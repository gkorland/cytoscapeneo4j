package com.redislabs.cytoscape.redisgraph.internal.graph.commands;

public class CommandException extends Exception {
    public CommandException(Exception e) {
        super(e);
    }
}
