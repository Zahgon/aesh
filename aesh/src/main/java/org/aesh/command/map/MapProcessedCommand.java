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
package org.aesh.command.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.impl.internal.ParsedCommand;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.parser.CommandLineParser.Mode;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.parser.OptionParserException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.result.ResultHandler;
import org.aesh.command.validator.CommandValidator;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class MapProcessedCommand<CI extends CommandInvocation> extends ProcessedCommand<MapCommand<CI>, CI> {

    private final MapProcessedOptionProvider provider;

    private List<ProcessedOption> currentOptions;

    private final boolean initialized;

    private final boolean lookup;

    private Mode mode;

    private static final MapProcessedOptionProvider EMPTY_PROVIDER = options -> Collections.emptyList();

    MapProcessedCommand(String name, List<String> aliases, MapCommand<CI> command, String description, CommandValidator<MapCommand<CI>, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, MapProcessedOptionProvider provider, CommandActivator activator, boolean lookup) throws OptionParserException {
        super(name, aliases, command, description, validator, resultHandler, generateHelp, disableParsing, version, arguments, options, argument, populator, activator);
        initialized = true;
        this.provider = provider == null ? EMPTY_PROVIDER : provider;
        this.lookup = lookup;
    }

    @Override
    protected void updateOptionsInvocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<ProcessedOption> getOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasAskIfNotSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedOption searchAllOptions(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedOption findLongOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedOption findLongOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clearOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<ProcessedOption> getCurrentOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getOptions(boolean dynamic) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMode(Mode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
