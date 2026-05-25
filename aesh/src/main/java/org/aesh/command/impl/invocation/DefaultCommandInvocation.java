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

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.aesh.command.CommandException;
import org.aesh.command.CommandNotFoundException;
import org.aesh.command.CommandRuntime;
import org.aesh.command.Executor;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.CommandInvocationConfiguration;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.shell.Shell;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.Key;
import org.aesh.terminal.KeyAction;
import org.aesh.terminal.tty.Size;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class DefaultCommandInvocation implements CommandInvocation {

    private final Shell shell;

    private final CommandRuntime<DefaultCommandInvocation> processor;

    private final CommandInvocationConfiguration config;

    private final CommandContainer<DefaultCommandInvocation> commandContainer;

    public DefaultCommandInvocation(CommandRuntime<DefaultCommandInvocation> processor, CommandInvocationConfiguration config, CommandContainer<DefaultCommandInvocation> commandContainer, Shell shell) {
        this.processor = processor;
        this.config = config;
        this.commandContainer = commandContainer;
        if (shell != null)
            this.shell = shell;
        else
            this.shell = new DefaultShell();
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
    public KeyAction input() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeyAction input(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String inputLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String inputLine(Prompt prompt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void executeCommand(String input) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, CommandException, InterruptedException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void print(String msg, boolean paging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void println(String msg, boolean paging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Executor<? extends CommandInvocation> buildExecutor(String line) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandInvocationConfiguration getConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static class DefaultShell implements Shell {

        @Override
        public void write(String out, boolean paging) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void writeln(String out, boolean paging) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void write(int[] out) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void write(char out) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String readLine() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String readLine(Prompt prompt) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Key read() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Key read(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Key read(Prompt prompt) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean enableAlternateBuffer() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean enableMainBuffer() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Size size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
