package com.example.carrotandstick.src.calendar2;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.carrotandstick.R;
import com.example.carrotandstick.src.BaseActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.calendar2.interfaces.Calendar2ActivityView;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;
import com.example.carrotandstick.src.calendar2.models.RequestCheck;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.SocialObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.carrotandstick.src.ApplicationClass.DATE_FORMAT;

public class CalendarActivity extends BaseActivity implements Calendar2ActivityView {

    List<EventDay> events;
    Calendar calendar;
    CalendarView calendarView;
    Button mBtnGoalcheck;
    Button mBtnGoalfinish;
    ImageView mBtnShare;
    CustomDialogDeleteGoal customDialogDeleteGoal;
    CustomDialogShare customDialogShare;
    Calendar2Service calendar2Service;
    int goalNo;
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);

        goalNo = getIntent().getIntExtra("goalNo", 1);
        String dday = getIntent().getStringExtra("goalDday");
        String goalName = getIntent().getStringExtra("goalName");

        calendar2Service = new Calendar2Service(this);
        mBtnGoalcheck = findViewById(R.id.calendar2_btn_goalcheck);


        TextView mTvDday = findViewById(R.id.calendar2_tv_dday);
        TextView mTvGoalname = findViewById(R.id.calendar2_tv_goal);
        mTvDday.setText(dday);
        mTvGoalname.setText(goalName);

        events = new ArrayList<>();
        calendar = Calendar.getInstance();

        calendar2Service.getGoalNo(goalNo);

        mBtnGoalcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCheck requestCheck = new RequestCheck();
                requestCheck.setGoalNo(goalNo);
                calendar2Service.postCheck(requestCheck);
            }
        });

        mBtnGoalfinish = findViewById(R.id.calendar2_btn_finish);
        mBtnGoalfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogDeleteGoal = new CustomDialogDeleteGoal(CalendarActivity.this, positive, negative);
                customDialogDeleteGoal.show();
            }

            private View.OnClickListener positive = new View.OnClickListener() {
                public void onClick(View v) {
                    calendar2Service.deleteCheck(goalNo);

                    customDialogDeleteGoal.dismiss();
                }
            };

            private View.OnClickListener negative = new View.OnClickListener() {
                public void onClick(View v) {
                    customDialogDeleteGoal.dismiss();
                }
            };
        });

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mBtnShare = findViewById(R.id.calendar2_iv_share);
        mBtnShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v = getWindow().getDecorView().getRootView();
                v.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false);
                String filePath = Environment.getExternalStorageDirectory() + "/Download/" + Calendar.getInstance().getTime().toString() + ".jpg";
                File fileScreenshot = new File(filePath);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(fileScreenshot);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                uri = Uri.fromFile(fileScreenshot);
                share();
            }
        });

    }

    void share() {
        customDialogShare = new CustomDialogShare(CalendarActivity.this, positive, negative);
        customDialogShare.show();
    }

    private View.OnClickListener positive = new View.OnClickListener() {
        public void onClick(View v) {
            StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());

            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            kakaolink(uri, "자랑 하기", "이만큼 했어!");
                        }
                    });
                }
            });
            customDialogShare.dismiss();
        }
    };

    private View.OnClickListener negative = new View.OnClickListener() {
        public void onClick(View v) {
            StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());

            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            hideProgressDialog();
                            kakaolink(uri, "잔소리 듣기", "혼내줘!");
                        }
                    });
                }
            });
            customDialogShare.dismiss();
        }
    };

    public void kakaolink(Uri url, String title, String sub) {
        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder(title,
                        url.toString(),
                        LinkObject.newBuilder().setWebUrl("https://developers.kakao.com")
                                .setMobileWebUrl("https://developers.kakao.com").build())
                        .setDescrption(sub)
                        .build())
                .setSocial(SocialObject.newBuilder().build())
                //.addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder().setWebUrl("'https://developers.kakao.com").setMobileWebUrl("'https://developers.kakao.com").build()))
                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl("'https://developers.kakao.com")
                        .setMobileWebUrl("'https://developers.kakao.com")
                        .setAndroidExecutionParams("key1=value1")
                        .setIosExecutionParams("key1=value1")
                        .build()))
                .build();

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendDefault(this, params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
            }
        });
    }


    @Override
    public void validateGoalNoSuccess(GoalNoResponse.Result result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            int size = result.getCheckResult().size();
            for (int i = 0; i < size; i++) {
                String str = result.getCheckResult().get(i).getCreatedAt().substring(0, 10);
                try {
                    Date date = DATE_FORMAT.parse(str);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                events.add(new EventDay(calendar, R.drawable.calendar2_ic));
            }
            System.out.println("호출 후 : " + events.size());
            calendarView.setEvents(events);
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateGoalNoFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validateCheckSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
            Calendar calendar2 = Calendar.getInstance();
            events.add(0, new EventDay(calendar2, R.drawable.calendar2_ic));
            System.out.println("추가 : " + events.size());
            calendarView.setEvents(events);

            RequestCheck requestCheck = new RequestCheck();
            requestCheck.setGoalNo(goalNo);
            calendar2Service.postCollection(requestCheck);

        } else {
            events.remove(0);
            System.out.println("제거 : " + events.size());
            calendarView.setEvents(events);
            showCustomToast(message);
        }
    }

    @Override
    public void validateCheckFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validateDeleteSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateDeleteFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validatePostCollectionSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
        } else {
        }
    }

    @Override
    public void validatePostCollectionFail(String msg) {
        showCustomToast(msg);
    }
}
