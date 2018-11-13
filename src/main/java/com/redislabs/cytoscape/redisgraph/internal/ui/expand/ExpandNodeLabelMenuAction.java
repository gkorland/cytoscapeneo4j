package com.redislabs.cytoscape.redisgraph.internal.ui.expand;

import com.redislabs.cytoscape.redisgraph.internal.Services;
import com.redislabs.cytoscape.redisgraph.internal.client.CypherQuery;
import com.redislabs.cytoscape.redisgraph.internal.client.ClientException;
import com.redislabs.cytoscape.redisgraph.internal.tasks.ExpandNodeTask;
import com.redislabs.cytoscape.redisgraph.internal.tasks.ExpandNodeTask.Direction;
import com.redislabs.cytoscape.redisgraph.internal.tasks.importgraph.DefaultImportStrategy;
import org.cytoscape.application.swing.CyMenuItem;
import org.cytoscape.application.swing.CyNodeViewContextMenuFactory;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import javax.swing.*;
import java.util.ArrayList;

public class ExpandNodeLabelMenuAction implements CyNodeViewContextMenuFactory {

    private DefaultImportStrategy importGraphStrategy;
    private Services services;
    private CyNetworkView networkView;
    private View<CyNode> nodeView;
    private JMenu menu;
    private Direction direction;

    public ExpandNodeLabelMenuAction(Services services) {
        super();
        this.importGraphStrategy = new DefaultImportStrategy();
        this.services = services;
    }

    public void addMenuItemsNodes(Record record) {
        ListValue result = (ListValue) record.get("r");
        ArrayList<String> nodeLabels = new ArrayList<String>();
        result.asList().forEach(v -> nodeLabels.add("`" + (String) v + "`"));
        String nodeLabel = String.join(":", nodeLabels);
        String menuTitle = this.direction == Direction.IN ? "<- " : " - ";
        menuTitle = menuTitle + nodeLabel.replace("`", "") + (this.direction == Direction.OUT ? " ->" : " - ");

        JMenuItem menuItem = new JMenuItem(menuTitle);

        ExpandNodeTask expandNodeTask = new ExpandNodeTask(nodeView, networkView, this.services, true);
        expandNodeTask.setNode(nodeLabel);
        menuItem.addActionListener(expandNodeTask);
        this.menu.add(menuItem);

    }


    @Override
    public CyMenuItem createMenuItem(CyNetworkView networkView, View<CyNode> nodeView) {
        this.networkView = networkView;
        this.nodeView = nodeView;
        CyNode cyNode = (CyNode) nodeView.getModel();
        try {
            this.menu = new JMenu("Expand node to:");

            Long refid = networkView.getModel().getRow(cyNode).get(this.importGraphStrategy.getRefIDName(), Long.class);

            this.direction = Direction.BIDIRECTIONAL;
            String query = "match (n)-[]-(r) where ID(n) = " + refid + " return distinct labels(r) as r";
            CypherQuery cypherQuery = CypherQuery.builder().query(query).build();
            StatementResult result = this.services.getRedisGraphClient().getResults(cypherQuery);
            result.forEachRemaining(this::addMenuItemsNodes);

            direction = Direction.IN;
            query = "match (n)<-[]-(r) where ID(n) = " + refid + " return distinct labels(r) as r";
            cypherQuery = CypherQuery.builder().query(query).build();
            result = this.services.getRedisGraphClient().getResults(cypherQuery);
            result.forEachRemaining(this::addMenuItemsNodes);

            this.direction = Direction.OUT;
            query = "match (n)-[]->(r) where ID(n) = " + refid + " return distinct labels(r) as r";
            cypherQuery = CypherQuery.builder().query(query).build();
            result = this.services.getRedisGraphClient().getResults(cypherQuery);
            result.forEachRemaining(this::addMenuItemsNodes);


            CyMenuItem cyMenuItem = new CyMenuItem(this.menu, 0.5f);

            return cyMenuItem;

        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return null;
    }

}
