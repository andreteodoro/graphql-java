package com.graphql.demo.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphql.demo.model.AuthContext;
import com.graphql.demo.model.User;
import com.graphql.demo.repository.LinkRepository;
import com.graphql.demo.repository.UserRepository;
import com.graphql.demo.resolvers.LinkResolver;
import com.graphql.demo.resolvers.Mutation;
import com.graphql.demo.resolvers.Query;
import com.graphql.demo.resolvers.SigninResolver;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
                .resolvers(
                        new Query(linkRepository),
                        new Mutation(linkRepository, userRepository),
                        new SigninResolver(),
                        new LinkResolver(userRepository))
                .build()
                .makeExecutableSchema();
    }

    @Override
    protected GraphQLContext createContext(final Optional<HttpServletRequest> request, final Optional<HttpServletResponse> response) {
        final User user = request
                .map(req -> req.getHeader("Authorization"))
                .filter(id -> !id.isEmpty())
                .map(id -> id.replace("Bearer ", ""))
                .map(userRepository::findById)
                .orElse(null);
        return new AuthContext(user, request, response);
    }
}
