package nl.corwur.cytoscape.redisgraph.internal.client;

public class ConnectionParameter {
    private final String host;
    private final String username;
    private final char[] password;

    public ConnectionParameter(String url, String username, char[] password) {
        this.host = url;
        this.username = username;
        this.password = password;
    }

    String getBoltUrl() {
        return "bolt://" + host;
    }

    String getUsername() {
        return username;
    }

    String getPasswordAsString() {
        return new String(password);
    }
}
