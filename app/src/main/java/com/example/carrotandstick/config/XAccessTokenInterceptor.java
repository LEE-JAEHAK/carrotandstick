package com.example.carrotandstick.config;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.carrotandstick.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.carrotandstick.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        System.out.println("Interceptor : " + jwtToken);

        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
            System.out.println("Interceptor : " + jwtToken);
        }
        return chain.proceed(builder.build());
    }
}
