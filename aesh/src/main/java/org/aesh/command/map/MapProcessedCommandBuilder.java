/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016 Red Hat Inc. and/or its affiliates and other contributors
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
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.validator.NullCommandValidator;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.result.ResultHandler;
import org.aesh.command.validator.CommandValidator;
import org.aesh.util.ReflectionUtil;

public class MapProcessedCommandBuilder<CI extends CommandInvocation> {

    private MapProcessedOptionProvider provider;

    private String name;

    private String description;

    private CommandValidator<MapCommand<CI>, CI> validator;

    private ResultHandler resultHandler;

    private ProcessedOption arguments;

    private ProcessedOption argument;

    private final List<ProcessedOption> options;

    private CommandPopulator<Object, CI> populator;

    private MapCommand<CI> command;

    private List<String> aliases;

    private CommandActivator activator;

    private boolean lookup;

    private boolean generateHelp;

    private boolean disableParsing;

    private String version;

    private MapProcessedCommandBuilder() {
        options = new ArrayList<>();
    }

    public static <T extends CommandInvocation> MapProcessedCommandBuilder<T> builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> lookupAtCompletionOnly(boolean lookup) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> name(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> name(List<String> aliases) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> description(String usage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> version(String version) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> generateHelp(boolean help) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> disableParsing(boolean disableParsing) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> optionProvider(MapProcessedOptionProvider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> arguments(ProcessedOption arguments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> argument(ProcessedOption argument) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> validator(CommandValidator<MapCommand<CI>, CI> validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public MapProcessedCommandBuilder<CI> validator(Class<? extends CommandValidator> validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private CommandValidator initValidator(Class<? extends CommandValidator> validator) {
        if (validator != null && !validator.equals(NullCommandValidator.class)) {
            return ReflectionUtil.newInstance(validator);
        } else {
            return null;
        }
    }

    public MapProcessedCommandBuilder<CI> resultHandler(Class<? extends ResultHandler> resultHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ResultHandler initResultHandler(Class<? extends ResultHandler> resultHandler) {
        if (resultHandler != null && !resultHandler.equals(org.aesh.command.impl.result.NullResultHandler.class)) {
            return ReflectionUtil.newInstance(resultHandler);
        } else {
            return null;
        }
    }

    public MapProcessedCommandBuilder<CI> resultHandler(ResultHandler resultHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * By default a map populator will inject values in the MapCommand
     *
     * @param populator
     * @return
     */
    public MapProcessedCommandBuilder<CI> populator(CommandPopulator<Object, CI> populator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> command(MapCommand<CI> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> command(Class<? extends MapCommand<CI>> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> addOption(ProcessedOption option) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> addOptions(List<ProcessedOption> options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public MapProcessedCommandBuilder<CI> activator(CommandActivator activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public MapProcessedCommand<CI> create() throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
