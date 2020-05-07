package com.example.carrotandstick.src.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestUser {
    @SerializedName("id")
    String id;

    @SerializedName("pw")
    String pw;

    @SerializedName("nickName")
    String nickName;

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
