package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.model.SigninPayload;
import com.graphql.demo.model.User;

public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(final SigninPayload payload) {
        return payload.getUser();
    }
}
