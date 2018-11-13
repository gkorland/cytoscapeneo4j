package com.redislabs.cytoscape.redisgraph.internal.tasks;

import com.redislabs.cytoscape.redisgraph.internal.Services;
import com.redislabs.cytoscape.redisgraph.internal.client.CypherQuery;
import com.redislabs.cytoscape.redisgraph.internal.tasks.importgraph.DefaultImportStrategy;

import java.text.MessageFormat;

/**
 * This class imports all nodes and edges from neo4j into cytoscape.
 */
public class ImportAllNodesAndEdgesFromNeo4JTask extends AbstractImportTask {

    public ImportAllNodesAndEdgesFromNeo4JTask(Services services, String networkName, String visualStyleTitle) {
        super(
                services,
                networkName,
                visualStyleTitle,
                new DefaultImportStrategy(),
                CypherQuery.builder().query(formatQuery(networkName)).build());
    }

    private static String formatQuery(String network) {
        return MessageFormat.format(TaskConstants.MATCH_ALL_NODES_AND_EDGES, network);
    }
}
