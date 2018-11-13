package com.redislabs.cytoscape.redisgraph.internal.ui.connect;

import com.redislabs.cytoscape.redisgraph.internal.Services;
import org.cytoscape.application.swing.AbstractCyAction;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConnectInstanceMenuAction extends AbstractCyAction {

    private static final String MENU_TITLE = "Connect to RedisGraph Instance";
    private static final String MENU_LOC = "Apps.Cypher Queries";
    private final transient ConnectToRedisGraph connectToNeo4j;

    public static ConnectInstanceMenuAction create(Services services) {
        return new ConnectInstanceMenuAction(ConnectToRedisGraph.create(services));
    }

    private ConnectInstanceMenuAction(ConnectToRedisGraph connectToNeo4j) {
        super(MENU_TITLE);
        this.connectToNeo4j = connectToNeo4j;
        setPreferredMenu(MENU_LOC);
        setMenuGravity(0.0f);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        connectToNeo4j.connect();
    }

}
