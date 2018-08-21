package nl.corwur.cytoscape.redisgraph.internal.tasks.querytemplate.mapping;

public interface MappingStrategyVisitor {
    void visit(GraphMapping graphMapping);

    void visit(CopyAllMappingStrategy copyAllMappingStrategy);
}
