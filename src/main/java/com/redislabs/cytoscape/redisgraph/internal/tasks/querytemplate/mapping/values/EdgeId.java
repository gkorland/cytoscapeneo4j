package com.redislabs.cytoscape.redisgraph.internal.tasks.querytemplate.mapping.values;

import com.redislabs.cytoscape.redisgraph.internal.graph.GraphEdge;

/**
 * This class implements the value expresion for the id of an edge.
 */
public class EdgeId implements ValueExpression<GraphEdge, Long> {
    @Override
    public Long eval(GraphEdge val) {
        return val.getId();
    }

    @Override
    public void accept(ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
