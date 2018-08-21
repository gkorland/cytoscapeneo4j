package nl.corwur.cytoscape.redisgraph.internal.tasks;

import nl.corwur.cytoscape.redisgraph.internal.Services;
import nl.corwur.cytoscape.redisgraph.internal.client.CypherQuery;
import nl.corwur.cytoscape.redisgraph.internal.tasks.importgraph.DefaultImportStrategy;

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
