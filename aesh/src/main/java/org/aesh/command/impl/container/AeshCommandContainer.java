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
package org.aesh.command.impl.container;

import org.aesh.command.Command;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.container.DefaultCommandContainer;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.parser.AeshCommandLineParser;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.parser.CommandLineParserException;

/**
 * @author Aesh team
 */
public class AeshCommandContainer<CI extends CommandInvocation> extends DefaultCommandContainer<CI> {

    private CommandLineParser<CI> parser;

    private String errorMessage;

    public AeshCommandContainer(CommandLineParser<CI> parser) {
        super();
        if (parser != null && parser.getProcessedCommand() != null) {
            this.parser = parser;
        }
    }

    public AeshCommandContainer(ProcessedCommand<Command<CI>, CI> processedCommand) {
        super();
        parser = new AeshCommandLineParser<>(processedCommand);
    }

    public AeshCommandContainer(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandLineParser<CI> getParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean haveBuildError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getBuildErrorMessage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addChild(CommandContainer<CI> commandContainer) throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addLazyChild(String name, Class<? extends Command> clazz) throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
