package com.redislabs.cytoscape.redisgraph.internal.tasks;

public class CancelTaskException extends RuntimeException {
    public CancelTaskException(String msg) {
        super(msg);
    }
}
