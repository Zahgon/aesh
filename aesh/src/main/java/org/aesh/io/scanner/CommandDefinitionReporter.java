package org.aesh.io.scanner;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.aesh.command.CommandDefinition;

public class CommandDefinitionReporter implements AnnotationDetector.TypeReporter {

    private List<String> commands;

    public CommandDefinitionReporter() {
        commands = new ArrayList<>();
    }

    public List<String> getCommands() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Annotation>[] annotations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
