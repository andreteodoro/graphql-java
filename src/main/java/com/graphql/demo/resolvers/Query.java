package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.demo.model.Link;
import com.graphql.demo.repository.LinkRepository;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
}
