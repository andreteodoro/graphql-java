package com.graphql.demo.repository;

import com.graphql.demo.model.Link;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class LinkRepository {

    private final MongoCollection<Document> links;

    public LinkRepository(final MongoCollection<Document> links) {
        this.links = links;
    }

    public List<Link> getAllLinks() {
        final List<Link> allLinks = new ArrayList<>();
        for (final Document doc : links.find()) {
            final Link link = new Link(
                    doc.get("_id").toString(),
                    doc.getString("url"),
                    doc.getString("description"),
                    doc.getString("postedBy")
            );
            allLinks.add(link);
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
}
