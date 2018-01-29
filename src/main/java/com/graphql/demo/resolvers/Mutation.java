package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.demo.model.*;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.repository.UserRepository;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public Mutation(final LinkRepository linkRepository, final UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public Link createLink(final String url, final String description, final DataFetchingEnvironment env) {
        final AuthContext context = env.getContext();
        final Link newLink = new Link(url, description, context.getUser().getId());
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(final String name, final AuthData auth) {
        final User newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }

    public SigninPayload signinUser(final AuthData auth) throws IllegalAccessException {
        final User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }
}
