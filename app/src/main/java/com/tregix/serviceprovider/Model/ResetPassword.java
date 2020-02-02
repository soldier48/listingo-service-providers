package com.tregix.serviceprovider.Model;

/**
 * Created by Gohar Ali on 2/1/2018.
 */

public class ResetPassword {

    private String email;

    public ResetPassword(String em) {
        email = em;
    }

    public ResetPassword() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
