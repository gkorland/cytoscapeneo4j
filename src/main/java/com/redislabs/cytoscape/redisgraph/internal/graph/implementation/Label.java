package com.redislabs.cytoscape.redisgraph.internal.graph.implementation;

public abstract class Label {
    private final String label;

    public Label(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
