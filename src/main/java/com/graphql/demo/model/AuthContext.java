package com.graphql.demo.model;

import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthContext extends GraphQLContext {

    private final User user;

    public AuthContext(final User user, final Optional<HttpServletRequest> request, final Optional<HttpServletResponse> response) {
        super(request, response);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
