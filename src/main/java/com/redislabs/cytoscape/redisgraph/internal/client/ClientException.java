package com.redislabs.cytoscape.redisgraph.internal.client;

public class ClientException extends Exception {
    public ClientException(String message, Throwable t) {
        super(message, t);
    }

    public ClientException() {
        super();
    }
}
