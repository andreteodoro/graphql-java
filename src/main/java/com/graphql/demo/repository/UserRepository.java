package com.graphql.demo.repository;

import com.graphql.demo.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UserRepository {

    private final MongoCollection<Document> users;

    public UserRepository(final MongoCollection<Document> users) {
        this.users = users;
    }

    public User findByEmail(final String email) {
        final Document doc = users.find(eq("email", email)).first();
        return user(doc);
    }

    public User findById(final String id) {
        final Document doc = users.find(eq("_id", new ObjectId(id))).first();
        return user(doc);
    }

    public User saveUser(final User user) {
        final Document doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        users.insertOne(doc);
        return new User(
                doc.get("_id").toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    private User user(final Document doc) {
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }
}
