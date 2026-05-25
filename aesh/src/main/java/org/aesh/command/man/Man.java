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
package org.aesh.command.man;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.aesh.command.CommandDefinition;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.completer.CompleterInvocation;
import org.aesh.command.completer.OptionCompleter;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.man.parser.ManFileParser;
import org.aesh.command.option.Arguments;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.settings.ManProvider;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Config;

/**
 * A Man implementation for Aesh. ref: http://en.wikipedia.org/wiki/Man_page
 *
 * @author Aesh team
 */
@CommandDefinition(name = "man", description = "manuals")
public class Man extends AeshFileDisplayer {

    @Arguments(completer = ManCompleter.class)
    private final List<String> manPages;

    private final ManFileParser fileParser;

    private CommandRegistry<? extends CommandInvocation> registry;

    private final ManProvider manProvider;

    public Man(ManProvider manProvider) {
        super();
        this.manProvider = manProvider;
        manPages = new ArrayList<>();
        fileParser = new ManFileParser();
    }

    public void setRegistry(CommandRegistry<? extends CommandInvocation> registry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public FileParser getFileParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void displayBottom() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public class ManCompleter implements OptionCompleter {

        @Override
        public void complete(CompleterInvocation completerData) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
