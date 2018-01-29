package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.model.Link;
import com.graphql.demo.model.User;
import com.graphql.demo.model.Vote;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.repository.UserRepository;

public class VoteResolver implements GraphQLResolver<Vote> {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public VoteResolver(final LinkRepository linkRepository, final UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public User user(final Vote vote) {
        return userRepository.findById(vote.getUserId());
    }

    public Link link(final Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    }
}
