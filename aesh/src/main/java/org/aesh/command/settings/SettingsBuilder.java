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
package org.aesh.command.settings;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import org.aesh.command.CommandNotFoundHandler;
import org.aesh.command.activator.CommandActivatorProvider;
import org.aesh.command.activator.OptionActivatorProvider;
import org.aesh.command.completer.CompleterInvocationProvider;
import org.aesh.command.converter.ConverterInvocationProvider;
import org.aesh.command.export.ExportChangeListener;
import org.aesh.command.impl.invocation.AeshInvocationProviders;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.CommandInvocationProvider;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.validator.ValidatorInvocationProvider;
import org.aesh.console.AeshContext;
import org.aesh.io.Resource;
import org.aesh.readline.alias.AliasManager;
import org.aesh.readline.editing.EditMode;
import org.aesh.terminal.Connection;
import org.aesh.terminal.utils.LoggerUtil;

/**
 * @author Aesh team
 */
public class SettingsBuilder<CI extends CommandInvocation> {

    private SettingsImpl<CI> settings;

    public static <CI extends CommandInvocation> SettingsBuilder<CI> builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private SettingsBuilder() {
        settings = new SettingsImpl<>();
    }

    public SettingsBuilder(Settings<CI> baseSettings) {
        settings = new SettingsImpl<>(baseSettings);
    }

    public SettingsBuilder<CI> mode(EditMode.Mode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> historyFile(File history) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> historyFilePermission(FileAccessPermission fileAccessPermission) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> historySize(int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> bellStyle(String bellStyle) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> inputStream(InputStream inputStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> outputStream(PrintStream outputStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> outputStreamError(PrintStream error) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> inputrc(File inputrc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> logging(boolean logging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> disableCompletion(boolean disableCompletion) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> logfile(String logFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> readInputrc(boolean readInputrc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> disableHistory(boolean disableHistory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> persistHistory(boolean persistHistory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> aliasFile(File aliasFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> enableAlias(boolean enableAlias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> persistAlias(boolean persistAlias) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> aliasManager(AliasManager aliasManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> quitHandler(QuitHandler quitHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> parseOperators(boolean parseOperators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> enableMan(boolean enableMan) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> aeshContext(AeshContext aeshContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> enableExport(boolean enableExport) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> exportFile(File exportFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setPersistExport(boolean persistExport) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setExportUsesSystemEnvironment(boolean isLoad) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setFileResource(Resource resource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setExecuteAtStart(String execute) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setExecuteFileAtStart(Resource executeFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> commandActivatorProvider(CommandActivatorProvider commandActivatorProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> optionActivatorProvider(OptionActivatorProvider optionActivatorProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> commandRegistry(CommandRegistry<CI> commandRegistry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> commandInvocationProvider(CommandInvocationProvider<CI> commandInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> commandNotFoundHandler(CommandNotFoundHandler commandNotFoundHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> completerInvocationProvider(CompleterInvocationProvider completerInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> converterInvocationProvider(ConverterInvocationProvider converterInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> validatorInvocationProvider(ValidatorInvocationProvider validatorInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> manProvider(ManProvider manProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> invocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> connection(Connection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> enableOperatorParser(boolean enabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> exportListener(ExportChangeListener listener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> echoCtrl(boolean echo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> redrawPromptOnInterrupt(boolean redraw) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setInterruptHandler(Consumer<Void> consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setConnectionClosedHandler(Consumer<Void> consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> setScanForCommandPackages(String... packages) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> enableSearchInPaging(boolean enable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SettingsBuilder<CI> subCommandModeSettings(SubCommandModeSettings subCommandModeSettings) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Settings<CI> build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
