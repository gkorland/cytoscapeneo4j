package nl.corwur.cytoscape.redisgraph.internal.ui.connect;

import nl.corwur.cytoscape.redisgraph.internal.Services;
import nl.corwur.cytoscape.redisgraph.internal.client.Client;
import nl.corwur.cytoscape.redisgraph.internal.configuration.AppConfiguration;

import org.cytoscape.application.swing.CySwingApplication;

import javax.swing.*;

public class ConnectToRedisGraph {

    private final Client redisGraphClient;
    private final CySwingApplication cySwingApplication;
    private final AppConfiguration appConfiguration;

    private ConnectToRedisGraph(Client redisGraphClient, CySwingApplication cySwingApplication, AppConfiguration appConfiguration) {
        this.redisGraphClient = redisGraphClient;
        this.cySwingApplication = cySwingApplication;
        this.appConfiguration = appConfiguration;
    }

    public static ConnectToRedisGraph create(Services services) {
        return new ConnectToRedisGraph(
                services.getRedisGraphClient(),
                services.getCySwingApplication(),
                services.getAppConfiguration());
    }


    public boolean openConnectDialogIfNotConnected() {
        if (redisGraphClient.isConnected()) {
            return true;
        }
        return connect();
    }

    public boolean connect() {
        ConnectDialog connectDialog = new ConnectDialog(cySwingApplication.getJFrame(), redisGraphClient::connect,
                appConfiguration.getNeo4jHost(),
                appConfiguration.getNeo4jUsername()
        );
        connectDialog.showConnectDialog();
        if (connectDialog.isOk()) {
            appConfiguration.setConnectionParameters(connectDialog.getHostname(), connectDialog.getUsername());
            appConfiguration.save();
            JOptionPane.showMessageDialog(this.cySwingApplication.getJFrame(), "Connected");
        }
        return redisGraphClient.isConnected();
    }
}
