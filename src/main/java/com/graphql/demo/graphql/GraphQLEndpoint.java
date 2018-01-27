package com.graphql.demo.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.repository.UserRepository;
import com.graphql.demo.resolvers.Mutation;
import com.graphql.demo.resolvers.Query;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;

    static {
        final MongoDatabase mongo = new MongoClient().getDatabase("graphql-java");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository), new Mutation(linkRepository, userRepository))
                .build()
                .makeExecutableSchema();
    }
}
