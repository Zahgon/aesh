/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesh.command.impl.invocation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.aesh.command.Command;
import org.aesh.command.CommandException;
import org.aesh.command.CommandNotFoundException;
import org.aesh.command.CommandRuntime;
import org.aesh.command.Executor;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.impl.context.CommandContext;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.impl.shell.ShellOutputDelegate;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.CommandInvocationConfiguration;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.shell.Shell;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.console.Console;
import org.aesh.console.ReadlineConsole;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.KeyAction;

/**
 * @author Aesh team
 */
public final class AeshCommandInvocation implements CommandInvocation {

    private final Console console;

    private final Shell shell;

    private final CommandRuntime<AeshCommandInvocation> runtime;

    private final CommandInvocationConfiguration config;

    private final CommandContainer<AeshCommandInvocation> commandContainer;

    private final CommandContext commandContext;

    private java.io.InputStream cachedStdin;

    private boolean stdinResolved;

    public AeshCommandInvocation(Console console, Shell shell, CommandRuntime<AeshCommandInvocation> runtime, CommandInvocationConfiguration config, CommandContainer<AeshCommandInvocation> commandContainer) {
        this(console, shell, runtime, config, commandContainer, null);
    }

    public AeshCommandInvocation(Console console, Shell shell, CommandRuntime<AeshCommandInvocation> runtime, CommandInvocationConfiguration config, CommandContainer<AeshCommandInvocation> commandContainer, CommandContext commandContext) {
        this.console = console;
        this.runtime = runtime;
        this.config = config;
        this.commandContainer = commandContainer;
        this.commandContext = commandContext;
        //if we have output redirection, use output delegate
        if (getConfiguration() != null && getConfiguration().getOutputRedirection() != null) {
            this.shell = new ShellOutputDelegate(shell, getConfiguration().getOutputRedirection());
        } else
            //use default shell for no redirections
            this.shell = shell;
    }

    @Override
    public Shell getShell() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setPrompt(Prompt prompt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Prompt getPrompt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getHelpInfo(String commandName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getHelpInfo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeyAction input() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeyAction input(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String inputLine() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String inputLine(Prompt prompt) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void executeCommand(String input) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, CommandException, InterruptedException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Executor<AeshCommandInvocation> buildExecutor(String line) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void print(String msg, boolean page) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void println(String msg, boolean page) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandInvocationConfiguration getConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public java.io.InputStream getStdin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandContext getCommandContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enterSubCommandMode(Command<?> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean exitSubCommandMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
