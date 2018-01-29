package com.graphql.demo.model;

public class SigninPayload {

    private final String token;
    private final User user;

    public SigninPayload(final String token, final User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
