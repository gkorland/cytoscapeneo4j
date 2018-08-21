package nl.corwur.cytoscape.redisgraph.internal.client;

import nl.corwur.cytoscape.redisgraph.internal.graph.GraphEdge;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphLong;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphNode;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphObject;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphObjectList;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphPath;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphResult;
import nl.corwur.cytoscape.redisgraph.internal.graph.GraphUnspecifiedType;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.v1.Value;
//import org.neo4j.driver.v1.Record;
//import org.neo4j.driver.v1.Value;
//import org.neo4j.driver.v1.types.Entity;
import org.neo4j.driver.v1.types.Node;
//import org.neo4j.driver.v1.types.Path;
//import org.neo4j.driver.v1.types.Relationship;
//import org.neo4j.driver.v1.util.Pair;
import org.neo4j.driver.v1.types.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.redislabs.redisgraph.Record;

class GraphFactory {

	final static private AtomicLong relationshipId = new AtomicLong(0);  
	
    GraphObject create(Record record) {
    	
        GraphResult neo4jResult = new GraphResult();
        
        
        Map<String, Value> sourceProps = new HashMap<String, Value>();
        Map<String, Value> targetProps = new HashMap<String, Value>();
        
        for (String key : record.keys()) {
        	if(key.startsWith("n.")) {
        		sourceProps.put(key.substring(2), new StringValue(record.getString(key))); 
        	} else if (key.startsWith("m.")) {
        		targetProps.put(key.substring(2), new StringValue(record.getString(key)));
        	}
        }
        
        Node sourceNode = new InternalNode(Long.parseLong(record.getString("id(n)")), new ArrayList<>() , sourceProps);
        Node targetNode = new InternalNode(Long.parseLong(record.getString("id(m)")), new ArrayList<>() , targetProps);
        Relationship relationship = new InternalRelationship(relationshipId.incrementAndGet(), sourceNode.id(), targetNode.id(), "");
        neo4jResult.add("n", create(sourceNode));
        neo4jResult.add("m", create(targetNode));
        neo4jResult.add( "r", create(relationship));
        
        return neo4jResult;
    }

    private GraphObject create(Value value) {
        switch (value.type().name()) {
            case "NODE":
                return create(value.asNode());
            case "RELATIONSHIP":
                return create(value.asRelationship());
//            case "PATH":
//                return create(value.asPath());
//            case "BOOLEAN":
//                return create(value.asRelationship());
//            case "INTEGER":
//                return create(value.asLong());
//            case "LIST OF ANY?":
//                return create(value.asList());
            default:
                return new GraphUnspecifiedType();
        }
    }

//    private GraphObject create(List<Object> objects) {
//        return objects.stream()
//                .filter(o -> o instanceof Entity)
//                .map(o -> this.create((Entity) o))
//                .collect(GraphObjectList::new, (list, o) -> list.add(o), (list1, list2) -> list1.addAll(list2));
//    }

//    private GraphObject create(Entity entity) {
//        if (entity instanceof Relationship) {
//            return create((Relationship) entity);
//        } else if (entity instanceof Node) {
//            return create((Node) entity);
//        }
//        throw new IllegalStateException();
//    }

//    private GraphObject create(Path path) {
//
//        GraphPath graphPath = new GraphPath();
//        for (Node node : path.nodes()) {
//            graphPath.add(create(node));
//        }
//        for (Relationship relationship : path.relationships()) {
//            graphPath.add(create(relationship));
//        }
//        return graphPath;
//    }

//    private GraphLong create(long value) {
//        return new GraphLong(value);
//    }

    private GraphEdge create(Relationship relationship) {
        GraphEdge graphEdge = new GraphEdge();
        graphEdge.setStart(relationship.startNodeId());
        graphEdge.setEnd(relationship.endNodeId());
        graphEdge.setProperties(relationship.asMap());
        graphEdge.setType(relationship.type());
        graphEdge.setId(relationship.id());
        return graphEdge;
    }

    private GraphNode create(Node node) {
    	GraphNode graphNode = new GraphNode(node.id());
        graphNode.setProperties(node.asMap());
//        for (String label : node.labels()) {
//            graphNode.addLabel(label);
//        }
        return graphNode;
    }
}
