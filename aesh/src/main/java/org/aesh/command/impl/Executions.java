/*
 * JBoss, Home of Professional Open Source
 * Copyright 2017 Red Hat Inc. and/or its affiliates and other contributors
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
package org.aesh.command.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.aesh.command.Command;
import org.aesh.command.CommandException;
import org.aesh.command.CommandLifecycle;
import org.aesh.command.CommandNotFoundException;
import org.aesh.command.CommandResult;
import org.aesh.command.Executable;
import org.aesh.command.Execution;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.impl.completer.CompleterData;
import org.aesh.command.impl.context.CommandContext;
import org.aesh.command.impl.internal.OptionType;
import org.aesh.command.impl.internal.ParsedCommand;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.operator.AndOperator;
import org.aesh.command.impl.operator.ConfigurationOperator;
import org.aesh.command.impl.operator.DataProvider;
import org.aesh.command.impl.operator.EndOperator;
import org.aesh.command.impl.operator.ExecutableOperator;
import org.aesh.command.impl.operator.InputDelegate;
import org.aesh.command.impl.operator.InputRedirectionOperator;
import org.aesh.command.impl.operator.Operator;
import org.aesh.command.impl.operator.OrOperator;
import org.aesh.command.impl.operator.OutputRedirectionOperator;
import org.aesh.command.impl.operator.PipeOperator;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.CommandInvocationConfiguration;
import org.aesh.command.operator.OperatorType;
import org.aesh.command.option.ParentCommand;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.result.ResultHandler;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.console.AeshContext;
import org.aesh.parser.ParsedLine;
import org.aesh.readline.prompt.Prompt;
import org.aesh.selector.Selector;
import org.aesh.terminal.formatting.TerminalString;

/**
 * @author Aesh team
 */
@SuppressWarnings("unchecked")
class Executions {

    private static class ExecutionImpl<T extends CommandInvocation> implements Execution<T> {

        private final ExecutableOperator<T> executable;

        private ProcessedCommand<Command<T>, T> cmd;

        private final CommandInvocationConfiguration invocationConfiguration;

        private final AeshCommandRuntime<T> runtime;

        private final CommandContainer<T> commandContainer;

        private CommandResult result;

        private boolean populated;

        ExecutionImpl(ExecutableOperator<T> executable, AeshCommandRuntime<T> runtime, CommandInvocationConfiguration invocationConfiguration, CommandContainer<T> commandContainer) {
            this.executable = executable;
            this.runtime = runtime;
            this.invocationConfiguration = invocationConfiguration;
            this.commandContainer = commandContainer;
            this.cmd = commandContainer.getParser().getProcessedCommand();
        }

        @Override
        public T getCommandInvocation() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the executable
         */
        @Override
        public Executable getExecutable() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the cmd
         */
        @Override
        public Command<T> getCommand() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void populateCommand() throws CommandLineParserException, OptionValidatorException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Walk the parser tree and call afterParse() on parent group commands
         * whose child was actually parsed. Skips the final parsed command
         * (that's handled separately).
         */
        private void callAfterParseOnParents(CommandLineParser<?> rootParser) {
            if (!rootParser.isGroupCommand())
                return;
            // If the root parser itself is a group and has a parsed child,
            // the root is a parent — call its afterParse() first
            CommandLineParser<?> parsed = rootParser.parsedCommand();
            if (parsed != null && parsed != rootParser) {
                Command<?> parentCmd = rootParser.getProcessedCommand().getCommand();
                if (parentCmd instanceof CommandLifecycle) {
                    ((CommandLifecycle) parentCmd).afterParse();
                }
            }
        }

        @Override
        public ResultHandler getResultHandler() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public CommandResult execute() throws CommandException, InterruptedException, CommandValidatorException, CommandLineParserException, OptionValidatorException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Returns the CommandResult or null if not executed.
         *
         * @return
         */
        @Override
        public CommandResult getResult() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void setResult(CommandResult result) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void clearQueuedLine() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private enum State {

        NEED_COMMAND, NEED_OPERATOR, NEED_ARGUMENT
    }

    static <CI extends CommandInvocation> List<Execution<CI>> buildExecution(List<ParsedLine> fullLine, AeshCommandRuntime<CI> runtime) throws CommandNotFoundException, CommandLineParserException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static Operator buildOperator(OperatorType op, AeshContext context) {
        if (op == null) {
            return null;
        }
        switch(op) {
            case NONE:
            case END:
                {
                    return new EndOperator();
                }
            case REDIRECT_OUT:
                {
                    return new OutputRedirectionOperator(context);
                }
            case APPEND_OUT:
                {
                    return new OutputRedirectionOperator(context, true);
                }
            case PIPE:
                {
                    return new PipeOperator(context);
                }
            case REDIRECT_IN:
                {
                    return new InputRedirectionOperator(context);
                }
            case AND:
                {
                    return new AndOperator();
                }
            case OR:
                {
                    return new OrOperator();
                }
        }
        throw new IllegalArgumentException("Unsupported operator " + op);
    }

    private static void injectParentCommands(ProcessedCommand<?, ?> processedCommand, CommandContext commandContext) {
        java.util.function.BiConsumer<Object, Object> injector = processedCommand.getParentCommandInjector();
        if (injector != null) {
            Command<?> parent = commandContext.getParentCommand();
            if (parent != null)
                injector.accept(processedCommand.getCommand(), parent);
            return;
        }
        Object command = processedCommand.getCommand();
        for (Class<?> c = command.getClass(); c != null; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ParentCommand.class)) {
                    Class<?> fieldType = field.getType();
                    @SuppressWarnings("unchecked")
                    Command<?> parent = commandContext.getParentCommand((Class<? extends Command<?>>) fieldType.asSubclass(Command.class));
                    if (parent != null) {
                        try {
                            if (!Modifier.isPublic(field.getModifiers()))
                                field.setAccessible(true);
                            field.set(command, parent);
                        } catch (IllegalAccessException e) {
                            // Field injection failed, continue
                        }
                    }
                }
            }
        }
    }
}
