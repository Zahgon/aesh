package org.aesh.command.impl.internal;

public class ParsedOption {

    private final ProcessedOption processedOption;

    public ParsedOption(ProcessedOption po) {
        this.processedOption = po;
    }

    public String value() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String name() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
