package com.graphql.demo.model;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String password;

    public User(final String name, final String email, final String password) {
        this(null, name, email, password);
    }

    public User(final String id, final String name, final String email, final String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
