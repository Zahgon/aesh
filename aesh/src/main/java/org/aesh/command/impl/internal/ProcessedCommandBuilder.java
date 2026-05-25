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
package org.aesh.command.impl.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.aesh.command.Command;
import org.aesh.command.DefaultValueProvider;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.impl.activator.NullCommandActivator;
import org.aesh.command.impl.provider.NullDefaultValueProvider;
import org.aesh.command.impl.validator.NullCommandValidator;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.result.ResultHandler;
import org.aesh.command.validator.CommandValidator;
import org.aesh.util.ReflectionUtil;

/**
 * Build a {@link ProcessedCommand} object using the Builder pattern.
 *
 * @author Aesh team
 * @author Aesh team
 */
public class ProcessedCommandBuilder<C extends Command<CI>, CI extends CommandInvocation> {

    private String name;

    private String description;

    private CommandValidator<C, CI> validator;

    private ResultHandler resultHandler;

    private ProcessedOption arguments;

    private ProcessedOption arg;

    private final List<ProcessedOption> options;

    private CommandPopulator<Object, CI> populator;

    private C command;

    private List<String> aliases;

    private CommandActivator activator;

    private boolean generateHelp;

    private boolean disableParsing;

    private boolean stopAtFirstPositional;

    private DefaultValueProvider defaultValueProvider;

    private String version;

    private String helpUrl;

    private boolean sortOptions;

    private ProcessedCommandBuilder() {
        options = new ArrayList<>();
    }

    public static <T extends Command<I>, I extends CommandInvocation> ProcessedCommandBuilder<T, I> builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> name(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> aliases(List<String> aliases) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> description(String usage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> version(String version) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> generateHelp(boolean help) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> disableParsing(boolean disableParsing) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> stopAtFirstPositional(boolean stopAtFirstPositional) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> defaultValueProvider(DefaultValueProvider defaultValueProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> defaultValueProvider(Class<? extends DefaultValueProvider> defaultValueProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private DefaultValueProvider initDefaultValueProvider(Class<? extends DefaultValueProvider> defaultValueProvider) {
        if (defaultValueProvider != null && defaultValueProvider != NullDefaultValueProvider.class)
            return ReflectionUtil.newInstance(defaultValueProvider);
        else
            return null;
    }

    public ProcessedCommandBuilder<C, CI> helpUrl(String helpUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> sortOptions(boolean sortOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> arguments(ProcessedOption arguments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> argument(ProcessedOption argument) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> validator(CommandValidator<C, CI> validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> validator(Class<? extends CommandValidator<C, CI>> validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private CommandValidator<C, CI> initValidator(Class<? extends CommandValidator<C, CI>> validator) {
        if (validator != null && !validator.equals(NullCommandValidator.class))
            return ReflectionUtil.newInstance(validator);
        else
            return null;
    }

    public ProcessedCommandBuilder<C, CI> resultHandler(Class<? extends ResultHandler> resultHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ResultHandler initResultHandler(Class<? extends ResultHandler> resultHandler) {
        if (resultHandler != null && !resultHandler.equals(org.aesh.command.impl.result.NullResultHandler.class))
            return ReflectionUtil.newInstance(resultHandler);
        else
            return null;
    }

    public ProcessedCommandBuilder<C, CI> resultHandler(ResultHandler resultHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> populator(CommandPopulator<Object, CI> populator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> activator(CommandActivator activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> activator(Class<? extends CommandActivator> activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private CommandActivator initActivator(Class<? extends CommandActivator> activator) {
        if (activator != null && activator != NullCommandActivator.class)
            return ReflectionUtil.newInstance(activator);
        else
            return null;
    }

    public ProcessedCommandBuilder<C, CI> command(C command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public ProcessedCommandBuilder<C, CI> command(Class command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> addOption(ProcessedOption option) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommandBuilder<C, CI> addOptions(List<ProcessedOption> options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public ProcessedCommand<C, CI> create() throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
