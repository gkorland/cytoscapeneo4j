package com.redislabs.cytoscape.redisgraph.internal.tasks;

import com.redislabs.cytoscape.redisgraph.internal.Services;
import com.redislabs.cytoscape.redisgraph.internal.tasks.querytemplate.CypherQueryTemplate;

/**
 * This class imports the results of a query template from neo4j into cytoscape.
 */
public class ImportQueryTemplateTask extends AbstractImportTask {

    public ImportQueryTemplateTask(Services services, String networkName, String visualStyleTitle, CypherQueryTemplate queryTemplate) {
        super(services, networkName, visualStyleTitle, queryTemplate.getImportGraphStrategy(), queryTemplate.createQuery());
    }
}
