package nl.corwur.cytoscape.redisgraph.internal.graph.commands;

public abstract class Command {
    public abstract void execute() throws CommandException;
}
