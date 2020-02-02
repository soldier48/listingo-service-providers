package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gohar Ali on 12/22/2017.
 */

public class RegisterBusiness {

    @SerializedName("register")
    @Expose
    private RegisterUser register;

    public RegisterBusiness() {

    }

    public RegisterBusiness(RegisterUser user) {
        this.register = user;

    }

    public RegisterUser getRegister() {
        return register;
    }

    public void setRegister(RegisterUser register) {
        this.register = register;
    }
}
