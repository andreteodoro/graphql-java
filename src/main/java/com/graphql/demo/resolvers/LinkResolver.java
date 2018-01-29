package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.model.Link;
import com.graphql.demo.model.User;
import com.graphql.demo.repository.UserRepository;

public class LinkResolver implements GraphQLResolver<Link> {

    private final UserRepository userRepository;

    public LinkResolver(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(final Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}
