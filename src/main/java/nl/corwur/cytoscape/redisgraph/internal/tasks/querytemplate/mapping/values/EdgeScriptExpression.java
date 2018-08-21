package nl.corwur.cytoscape.redisgraph.internal.tasks.querytemplate.mapping.values;

import nl.corwur.cytoscape.redisgraph.internal.graph.GraphEdge;

/**
 * This class implements the value expression for evaluated javascriptcode.
 *
 * @param <V>
 */
public class EdgeScriptExpression<V> extends ValueScriptExpression<GraphEdge, V> {

    public EdgeScriptExpression(String script, Class<V> type) {
        super(script, "edge", type);
    }

    @Override
    public void accept(ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
