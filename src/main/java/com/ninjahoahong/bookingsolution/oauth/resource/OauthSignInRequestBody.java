package com.ninjahoahong.bookingsolution.oauth.resource;

public class OauthSignInRequestBody {
    private String token;

    private String fullName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
