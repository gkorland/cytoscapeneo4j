package com.redislabs.cytoscape.redisgraph.internal.tasks.querytemplate.mapping;

public interface MappingStrategy {
    void accept(MappingStrategyVisitor visitor);
}
