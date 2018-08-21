package nl.corwur.cytoscape.redisgraph.internal.tasks;

import nl.corwur.cytoscape.redisgraph.internal.Services;
import nl.corwur.cytoscape.redisgraph.internal.client.CypherQuery;
import nl.corwur.cytoscape.redisgraph.internal.tasks.importgraph.DefaultImportStrategy;

public class ImportQueryTask extends AbstractImportTask {
    public ImportQueryTask(Services services, String networkName, String visualStyle, DefaultImportStrategy defaultImportStrategy, CypherQuery query) {
        super(services, networkName, visualStyle, defaultImportStrategy, query);
    }
}
