package org.aesh.command.impl.internal;

import org.aesh.command.Command;

public class ParsedCommand {

    private final ProcessedCommand processedCommand;

    public ParsedCommand(ProcessedCommand pc) {
        this.processedCommand = pc;
    }

    public ParsedOption findLongOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Command command() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedOption argument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedOption arguments() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedOption findLongOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
