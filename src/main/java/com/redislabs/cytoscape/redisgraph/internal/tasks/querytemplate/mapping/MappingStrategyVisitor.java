package com.redislabs.cytoscape.redisgraph.internal.tasks.querytemplate.mapping;

public interface MappingStrategyVisitor {
    void visit(GraphMapping graphMapping);

    void visit(CopyAllMappingStrategy copyAllMappingStrategy);
}
