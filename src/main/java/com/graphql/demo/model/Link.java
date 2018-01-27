package com.graphql.demo.model;

public class Link {

    private final String id;
    private final String url;
    private final String description;

    public Link(final String url, final String description) {
        this(null, url, description);
    }

    public Link(final String id, final String url, final String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
