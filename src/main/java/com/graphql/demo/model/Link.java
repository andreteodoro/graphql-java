package com.graphql.demo.model;

public class Link {

    private final String id;
    private final String url;
    private final String description;
    private final String userId;

    public Link(final String url, final String description, final String userId) {
        this(null, url, description, userId);
    }

    public Link(final String id, final String url, final String description, final String userId) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }
}
