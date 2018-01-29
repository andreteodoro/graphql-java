package com.graphql.demo.model;

import java.time.ZonedDateTime;

public class Vote {

    private final String id;
    private final ZonedDateTime createdAt;
    private final String userId;
    private final String linkId;

    public Vote(final ZonedDateTime createdAt, final String userId, final String linkId) {
        this(null, createdAt, userId, linkId);
    }

    public Vote(final String id, final ZonedDateTime createdAt, final String userId, final String linkId) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.linkId = linkId;
    }

    public String getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getLinkId() {
        return linkId;
    }
}
