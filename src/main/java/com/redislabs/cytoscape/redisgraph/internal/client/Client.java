package com.redislabs.cytoscape.redisgraph.internal.client;

import com.redislabs.cytoscape.redisgraph.internal.graph.Graph;
import com.redislabs.cytoscape.redisgraph.internal.graph.GraphObject;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

//import org.neo4j.driver.v1.AuthTokens;
//import org.neo4j.driver.v1.Config;
//import org.neo4j.driver.v1.Driver;
//import org.neo4j.driver.v1.GraphDatabase;
//import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
//import org.neo4j.driver.v1.exceptions.AuthenticationException;
//import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;

import com.redislabs.redisgraph.RedisGraphAPI;
import com.redislabs.redisgraph.ResultSet;

public class Client {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Client.class);

//    private RedisGraphAPI driver;
    private GraphFactory neo4JGraphFactory = new GraphFactory();

    public boolean connect(ConnectionParameter connectionParameter) {
    	
    	new RedisGraphAPI("a");
    	
//        try {
        	
        	
//            driver = new RedisGraphAPI("a");
//                    connectionParameter.getBoltUrl(),
//                    AuthTokens.basic(
//                            connectionParameter.getUsername(),
//                            connectionParameter.getPasswordAsString()
//                    ),
//                    Config.build().withoutEncryption().toConfig()
//            );
            return true;
//        } catch (AuthenticationException | ServiceUnavailableException e) {
//            logger.warn("Cannot connect to Neo4j", e);
//            return false;
//        }
    }

    public void executeQuery(CypherQuery cypherQuery) throws ClientException {
//        try (Session session = driver.session()) {
//            session.run(cypherQuery.getQuery(), cypherQuery.getParams());
//        } catch (Exception e) {
//            throw new Neo4jClientException(e.getMessage(), e);
//        }
//    	driver.query(cypherQuery.getQuery());
    }

    public Graph getGraph(CypherQuery cypherQuery) throws ClientException {
    	return getGraph("a", cypherQuery); // TODO fix it
    }
    
    public Graph getGraph(String networkName, CypherQuery cypherQuery) throws ClientException {
//        try (Session session = driver.session()) {
//            StatementResult statementResult = session.run(cypherQuery.getQuery(), cypherQuery.getParams());
//            return Graph.createFrom(statementResult.list(neo4JGraphFactory::create));
//        } catch (Exception e) {
//            throw new Neo4jClientException(e.getMessage(), e);
//        }
    	ResultSet statementResult = new RedisGraphAPI(networkName).query(cypherQuery.getQuery());
    	
    	  if ( statementResult.hasNext() )
          {
              List<GraphObject> result = new ArrayList<>();

              do
              {
                  result.add( neo4JGraphFactory.create( statementResult.next() ) );
              }
              while ( statementResult.hasNext() );

              return Graph.createFrom(result);
          }
          else
          {
              return Graph.createFrom(emptyList());
          }
    	
    	
//    	StatementResult statementResult = session.run(cypherQuery.getQuery(), cypherQuery.getParams());
//    	Graph.createFrom(statementResult.list(neo4JGraphFactory::create));
    	
    	
//    	return Graph.createFrom(result.list(neo4JGraphFactory::create));
//    	return null;
    }

    public StatementResult getResults(CypherQuery cypherQuery) throws ClientException {
//        try (Session session = driver.session()) {
//            StatementResult statementResult = session.run(cypherQuery.getQuery(), cypherQuery.getParams());
//            return statementResult;
//        } catch (Exception e) {
//            throw new Neo4jClientException(e.getMessage(), e);
//        }
//    	driver.query(cypherQuery.getQuery());
    	return null;
    }

    public void explainQuery(CypherQuery cypherQuery) throws ClientException {
    	// TODO remote it
    }

    public void explainQuery(String networkName, CypherQuery cypherQuery) throws ClientException {
//        try (Session session = driver.session()) {
//            session.run(cypherQuery.getExplainQuery(), cypherQuery.getParams());
//        } catch (Exception e) {
//            throw new Neo4jClientException(e.getMessage(), e);
//        }
//    	driver.query(cypherQuery.getExplainQuery());    	
    }

    public boolean isConnected() {
//        return driver != null && driver.session().isOpen();
    	return true;
    }

    public void close() {
//        if (isConnected()) {
//            driver.close();
//        }
    }
}
