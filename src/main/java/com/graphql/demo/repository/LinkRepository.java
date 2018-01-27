package com.graphql.demo.repository;

import com.graphql.demo.model.Link;
import com.mongodb.client.MongoCollection;
import graphql.execution.batched.Batched;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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
    public List<Link> getAllLinks() {
        final List<Link> allLinks = new ArrayList<>();
        for (final Document doc : links.find()) {
            allLinks.add(link(doc));
        }
        return allLinks;
    }

    public void saveLink(final Link link) {
        final Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        links.insertOne(doc);
    }

    private Link link(final Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }
}
