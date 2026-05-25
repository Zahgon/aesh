package org.aesh.command.impl.invocation;

import org.aesh.command.CommandRuntime;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.invocation.CommandInvocationBuilder;
import org.aesh.command.invocation.CommandInvocationConfiguration;
import org.aesh.command.shell.Shell;

public class DefaultCommandInvocationBuilder implements CommandInvocationBuilder<DefaultCommandInvocation> {

    private final Shell shell;

    public DefaultCommandInvocationBuilder(Shell shell) {
        this.shell = shell;
    }

    @Override
    public DefaultCommandInvocation build(CommandRuntime<DefaultCommandInvocation> runtime, CommandInvocationConfiguration configuration, CommandContainer<DefaultCommandInvocation> commandContainer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
