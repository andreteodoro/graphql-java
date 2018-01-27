package com.graphql.demo.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.resolvers.Mutation;
import com.graphql.demo.resolvers.Query;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        final LinkRepository linkRepository = new LinkRepository();
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository), new Mutation(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}
