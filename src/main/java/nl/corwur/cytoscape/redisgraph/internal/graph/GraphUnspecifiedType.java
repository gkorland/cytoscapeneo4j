package nl.corwur.cytoscape.redisgraph.internal.graph;

/**
 * This class represents the error case that the type is unkown or unsupported.
 */
public class GraphUnspecifiedType implements GraphObject {
    @Override
    public void accept(GraphVisitor graphVisitor) {
        graphVisitor.visit(this);
    }
}
