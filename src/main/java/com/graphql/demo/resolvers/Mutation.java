package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.demo.model.Link;
import com.graphql.demo.repository.LinkRepository;

public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
