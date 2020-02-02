package com.tregix.serviceprovider.Model;

/**
 * Created by Confiz123 on 3/7/2018.
 */

public class TokenRequestParam {

    private String device_token;
    private int user_id;

    public TokenRequestParam(String token,int uid){
        device_token = token;
        user_id = uid;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
