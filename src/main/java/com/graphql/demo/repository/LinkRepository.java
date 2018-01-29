package com.graphql.demo.repository;

import com.graphql.demo.model.Link;
import com.graphql.demo.model.LinkFilter;
import com.mongodb.client.MongoCollection;
import graphql.execution.batched.Batched;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.*;

public class LinkRepository {

    private final MongoCollection<Document> links;

    public LinkRepository(final MongoCollection<Document> links) {
        this.links = links;
    }

    public Link findById(final String id) {
        final Document doc = links.find(eq("_id", new ObjectId(id))).first();
        return link(doc);
    }

    @Batched
    public List<Link> getAllLinks(final LinkFilter filter) {
        final Optional<Bson> mongoFilter = Optional.ofNullable(filter).map(this::buildFilter);

        final List<Link> allLinks = new ArrayList<>();
        for (final Document doc : mongoFilter.map(links::find).orElseGet(links::find)) {
            allLinks.add(link(doc));
        }
        return allLinks;
    }

    public void saveLink(final Link link) {
        final Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        doc.append("postedBy", link.getUserId());
        links.insertOne(doc);
    }

    private Link link(final Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }

    private Bson buildFilter(final LinkFilter filter) {
        final String descriptionPattern = filter.getDescriptionContains();
        final String urlPattern = filter.getUrlContains();
        Bson descriptionCondition = null;
        Bson urlCondition = null;
        if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
            descriptionCondition = regex("description", ".*" + descriptionPattern + ".*", "i");
        }
        if (urlPattern != null && !urlPattern.isEmpty()) {
            urlCondition = regex("url", ".*" + urlPattern + ".*", "i");
        }
        if (descriptionCondition != null && urlCondition != null) {
            return and(descriptionCondition, urlCondition);
        }
        return descriptionCondition != null ? descriptionCondition : urlCondition;
    }
}
