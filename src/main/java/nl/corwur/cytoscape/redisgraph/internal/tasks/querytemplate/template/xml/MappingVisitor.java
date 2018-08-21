package nl.corwur.cytoscape.redisgraph.internal.tasks.querytemplate.template.xml;

public interface MappingVisitor {
    void visit(ColumnMapping columnMapping);

    void visit(CopyAll copyAll);
}
