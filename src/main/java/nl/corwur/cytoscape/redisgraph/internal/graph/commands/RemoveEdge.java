package nl.corwur.cytoscape.redisgraph.internal.graph.commands;

import nl.corwur.cytoscape.redisgraph.internal.graph.implementation.GraphImplementationException;
import nl.corwur.cytoscape.redisgraph.internal.graph.implementation.PropertyKey;

/**
 * Remove an edge from a graph
 */
public class RemoveEdge extends GraphCommand {

    private final PropertyKey<Long> edgeId;

    private RemoveEdge(PropertyKey<Long> edgeId) {
        this.edgeId = edgeId;
    }

    public static RemoveEdge create(PropertyKey<Long> edgeId) {
        return new RemoveEdge(edgeId);
    }

    @Override
    public void execute() throws CommandException {
        try {
            graphImplementation.removeEdge(edgeId);
        } catch (GraphImplementationException e) {
            throw new CommandException(e);
        }
    }
}
