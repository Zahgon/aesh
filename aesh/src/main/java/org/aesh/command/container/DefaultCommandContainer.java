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
package org.aesh.command.container;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.aesh.command.Command;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.console.AeshContext;
import org.aesh.parser.ParsedLine;

/**
 * @author Aesh team
 */
public abstract class DefaultCommandContainer<CI extends CommandInvocation> implements CommandContainer<CI> {

    private ConcurrentLinkedQueue<ParsedLine> lines;

    public DefaultCommandContainer() {
    }

    private ConcurrentLinkedQueue<ParsedLine> lines() {
        if (lines == null)
            lines = new ConcurrentLinkedQueue<>();
        return lines;
    }

    @Override
    public void addLine(ParsedLine aeshLine) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ParsedLine pollLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void emptyLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedCommand<Command<CI>, CI> parseAndPopulate(InvocationProviders invocationProviders, AeshContext aeshContext) throws CommandLineParserException, OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedCommand<Command<CI>, CI> parseAndPopulate(InvocationProviders invocationProviders, AeshContext aeshContext, org.aesh.command.impl.context.CommandContext commandContext) throws CommandLineParserException, OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandContainerResult executeCommand(ParsedLine line, InvocationProviders invocationProviders, AeshContext aeshContext, CI commandInvocation) throws CommandLineParserException, OptionValidatorException, CommandValidatorException, CommandException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private CommandContainerResult executeCommand(InvocationProviders invocationProviders, AeshContext aeshContext, CI commandInvocation) throws CommandLineParserException, OptionValidatorException, CommandValidatorException, CommandException, InterruptedException {
        getParser().parsedCommand().getCommandPopulator().populateObject(getParser().parsedCommand().getProcessedCommand(), invocationProviders, aeshContext, CommandLineParser.Mode.VALIDATE);
        if (getParser().parsedCommand().getProcessedCommand().validator() != null && !getParser().parsedCommand().getProcessedCommand().hasOptionWithOverrideRequired()) {
            getParser().parsedCommand().getProcessedCommand().validator().validate(getParser().parsedCommand().getCommand());
        }
        CommandResult result = getParser().parsedCommand().getCommand().execute(commandInvocation);
        return new CommandContainerResult(getParser().parsedCommand().getProcessedCommand().resultHandler(), result);
    }

    @Override
    public String printHelp(String childCommandName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
