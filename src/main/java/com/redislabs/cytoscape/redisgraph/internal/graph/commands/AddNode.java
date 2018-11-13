package com.redislabs.cytoscape.redisgraph.internal.graph.commands;

import com.redislabs.cytoscape.redisgraph.internal.graph.implementation.GraphImplementationException;
import com.redislabs.cytoscape.redisgraph.internal.graph.implementation.NodeLabel;

import java.util.List;
import java.util.Map;

/**
 * This class implements the 'Add Node' command that can be executed by a Neo4jClient.
 */

public class AddNode extends GraphCommand {

    private Map<String, Object> properties;
    private List<NodeLabel> labels;

    private AddNode(List<NodeLabel> labels, Map<String, Object> properties) {
        this.labels = labels;
        this.properties = properties;
    }

    public static AddNode create(List<NodeLabel> labels, Map<String, Object> properties) {
        return new AddNode(labels, properties);
    }

    @Override
    public void execute() throws CommandException {
        try {
            graphImplementation.addNode(labels, properties);
        } catch (GraphImplementationException e) {
            throw new CommandException(e);
        }
    }
}
