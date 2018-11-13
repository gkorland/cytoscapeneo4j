package com.redislabs.cytoscape.redisgraph.internal.tasks.querytemplate.template.xml;

public interface Mapping {
    void accept(MappingVisitor visitor);
}
