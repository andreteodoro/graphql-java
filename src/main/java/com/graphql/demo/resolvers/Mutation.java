package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.demo.model.AuthData;
import com.graphql.demo.model.Link;
import com.graphql.demo.model.User;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.repository.UserRepository;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public Mutation(final LinkRepository linkRepository, final UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public Link createLink(final String url, final String description) {
        final Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(final String name, final AuthData auth) {
        final User newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }
}
