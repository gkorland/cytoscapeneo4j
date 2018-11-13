package com.redislabs.cytoscape.redisgraph.internal.tasks;

import com.redislabs.cytoscape.redisgraph.internal.Services;
import com.redislabs.cytoscape.redisgraph.internal.client.CypherQuery;
import com.redislabs.cytoscape.redisgraph.internal.tasks.importgraph.DefaultImportStrategy;

public class ImportQueryTask extends AbstractImportTask {
    public ImportQueryTask(Services services, String networkName, String visualStyle, DefaultImportStrategy defaultImportStrategy, CypherQuery query) {
        super(services, networkName, visualStyle, defaultImportStrategy, query);
    }
}
