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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import org.aesh.command.CommandNotFoundHandler;
import org.aesh.command.activator.CommandActivatorProvider;
import org.aesh.command.activator.OptionActivatorProvider;
import org.aesh.command.completer.CompleterInvocationProvider;
import org.aesh.command.converter.ConverterInvocationProvider;
import org.aesh.command.export.ExportChangeListener;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.CommandInvocationProvider;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.validator.ValidatorInvocationProvider;
import org.aesh.console.AeshContext;
import org.aesh.console.DefaultAeshContext;
import org.aesh.io.FileResource;
import org.aesh.io.Resource;
import org.aesh.readline.alias.AliasManager;
import org.aesh.readline.editing.EditMode;
import org.aesh.readline.editing.EditModeBuilder;
import org.aesh.terminal.Connection;
import org.aesh.terminal.utils.Config;

/**
 * Settings object that is parsed when Console is initialized.
 *
 * @author Aesh team
 */
public class SettingsImpl<CI extends CommandInvocation> implements Settings<CI> {

    private EditMode.Mode editMode = EditMode.Mode.EMACS;

    private File historyFile;

    private FileAccessPermission historyFilePermission;

    private int historySize = 500;

    private boolean historyDisabled = false;

    private boolean historyPersistent = true;

    private String bellStyle;

    private InputStream inputStream;

    private PrintStream stdOut;

    private PrintStream stdErr;

    private boolean readInputrc = true;

    private File inputrc;

    private boolean isLogging = false;

    private String logFile;

    private boolean disableCompletion = false;

    private QuitHandler quitHandler;

    private File aliasFile;

    private boolean aliasEnabled = true;

    private boolean persistAlias = true;

    private boolean enableOperatorParser = true;

    private boolean manEnabled = true;

    private AeshContext aeshContext;

    private boolean exportEnabled = true;

    private File exportFile;

    private boolean persistExport = true;

    private boolean exportUsesSystemEnvironment = false;

    private Resource resource;

    private String execute;

    private Resource executeFileAtStart;

    private CommandActivatorProvider commandActivatorProvider;

    private OptionActivatorProvider optionActivatorProvider;

    private CommandRegistry<CI> commandRegistry;

    private CommandInvocationProvider<CI> commandInvocationProvider;

    private CommandNotFoundHandler commandNotFoundHandler;

    private CompleterInvocationProvider completerInvocationProvider;

    private ConverterInvocationProvider converterInvocationProvider;

    private ValidatorInvocationProvider validatorInvocationProvider;

    private ManProvider manProvider;

    private Connection connection;

    private InvocationProviders invocationProviders;

    private ExportChangeListener exportListener;

    private boolean redrawPrompt = true;

    private boolean echoCtrl = true;

    private Consumer<Void> interruptHandler;

    private String[] scanPackages;

    private boolean enableSearchPaging;

    private AliasManager aliasManager;

    private Consumer<Void> connectionClosedHandler;

    private SubCommandModeSettings subCommandModeSettings;

    SettingsImpl() {
    }

    protected SettingsImpl(Settings<CI> baseSettings) {
        setMode(baseSettings.mode());
        setHistoryFile(baseSettings.historyFile());
        setHistoryFilePermission(baseSettings.historyFilePermission());
        setHistorySize(baseSettings.historySize());
        setBellStyle(baseSettings.bellStyle());
        setStdIn(baseSettings.stdIn());
        setStdOut(baseSettings.stdOut());
        setStdErr(baseSettings.stdErr());
        setInputrc(baseSettings.inputrc());
        setLogging(baseSettings.logging());
        setDisableCompletion(baseSettings.completionDisabled());
        setLogFile(baseSettings.logFile());
        setReadInputrc(baseSettings.readInputrc());
        setHistoryDisabled(baseSettings.historyDisabled());
        setHistoryPersistent(baseSettings.historyPersistent());
        setAliasFile(baseSettings.aliasFile());
        setAliasEnabled(baseSettings.aliasEnabled());
        setPersistAlias(baseSettings.persistAlias());
        setQuitHandler(baseSettings.quitHandler());
        enableOperatorParser(baseSettings.operatorParserEnabled());
        setManEnabled(baseSettings.manEnabled());
        setAeshContext(baseSettings.aeshContext());
        setExportEnabled(baseSettings.exportEnabled());
        setExportFile(baseSettings.exportFile());
        setPersistExport(baseSettings.persistExport());
        setResource(baseSettings.resource());
        setExportUsesSystemEnvironment(baseSettings.exportUsesSystemEnvironment());
        setExecuteAtStart(baseSettings.executeAtStart());
        setCommandActivatorProvider(baseSettings.commandActivatorProvider());
        setOptionActivatorProvider(baseSettings.optionActivatorProvider());
        setCommandRegistry(baseSettings.commandRegistry());
        setCommandInvocationProvider(baseSettings.commandInvocationProvider());
        setCommandNotFoundHandler(baseSettings.commandNotFoundHandler());
        setCompleterInvocationProvider(baseSettings.completerInvocationProvider());
        setConverterInvocationProvider(baseSettings.converterInvocationProvider());
        setValidatorInvocationProvider(baseSettings.validatorInvocationProvider());
        setManProvider(baseSettings.manProvider());
        setConnection(baseSettings.connection());
        setInvocationProviders(baseSettings.invocationProviders());
        setExportListener(baseSettings.exportListener());
        echoCtrl(baseSettings.isEchoCtrl());
        redrawPromptOnInterrupt(baseSettings.isRedrawPromptOnInterrupt());
        setInterruptHandler(baseSettings.getInterruptHandler());
        setScanForCommandPackages(baseSettings.getScanForCommandPackages());
        setEnableSearchInPaging(baseSettings.enableSearchInPaging());
        setAliasManager(baseSettings.aliasManager());
        setConnectionClosedHandler(baseSettings.connectionClosedHandler());
        setSubCommandModeSettings(baseSettings.subCommandModeSettings());
    }

    public void resetToDefaults() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Either Emacs or Vi mode.
     * Emacs is default if not set
     *
     * @return editing mode
     */
    @Override
    public EditMode.Mode mode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMode(EditMode.Mode editMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get EditMode based on os and mode
     *
     * @return edit mode
     */
    @Override
    public EditMode editMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the historyFilePermission
     */
    public FileAccessPermission historyFilePermission() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @param historyFilePermission the historyFilePermission to set
     */
    public void setHistoryFilePermission(FileAccessPermission historyFilePermission) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If not set the history file will be:
     * $HOME/.aesh_history
     *
     * @return history file
     */
    @Override
    public File historyFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHistoryFile(File historyFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * By default history size is 500
     * If its set to -1 the size is unlimited (Integer.MAX_VALUE)
     *
     * @return size
     */
    @Override
    public int historySize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * By default history size is 500
     * If its set to -1 the size is unlimited (Integer.MAX_VALUE)
     *
     * @param historySize size
     */
    public void setHistorySize(int historySize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * By default, bell style is noisy
     * NOTE: Not implemented yet
     *
     * @return bell style
     */
    @Override
    public String bellStyle() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setBellStyle(String bellStyle) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If not set, System.in will be used
     *
     * @return input
     */
    @Override
    public InputStream stdIn() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set where input is coming from
     *
     * @param inputStream input
     */
    public void setStdIn(InputStream inputStream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If not set System.out is used
     *
     * @return out
     */
    @Override
    public PrintStream stdOut() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set where output should go to
     *
     * @param stdOut output
     */
    public void setStdOut(PrintStream stdOut) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * If not set System.out is used
     *
     * @return out
     */
    @Override
    public PrintStream stdErr() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set where output should go to
     *
     * @param stdErr output
     */
    public void setStdErr(PrintStream stdErr) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the inputrc file, if not set it defaults to:
     * System.getProperty("user.home")+Config.getPathSeparator()+".inputrc"
     *
     * @return inputrc
     */
    @Override
    public File inputrc() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setInputrc(File inputrc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Are we logging?
     *
     * @return logging
     */
    @Override
    public boolean logging() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set logging, by default set to true
     *
     * @param logging do log
     */
    public void setLogging(boolean logging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Is completion disabled?
     * Set to false by default
     *
     * @return dis completion
     */
    @Override
    public boolean completionDisabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set to true do disable completion
     * Set to false by default
     *
     * @param disableCompletion dis
     */
    public void setDisableCompletion(boolean disableCompletion) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get log file
     *
     * @return log file
     */
    @Override
    public String logFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Specify a log file
     *
     * @param logFile file
     */
    public void setLogFile(String logFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Should we read config from inputrc
     * Set to true by default
     *
     * @return do we?
     */
    @Override
    public boolean readInputrc() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Specify if we should read config from inputrc
     * Set to true by default
     *
     * @param readInputrc specify
     */
    public void setReadInputrc(boolean readInputrc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Is history disabled
     * Set to true to disable history
     *
     * @return historyDisabled
     */
    @Override
    public boolean historyDisabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Is history disabled
     * Set to true to disable history
     *
     * @param historyDisabled history
     */
    public void setHistoryDisabled(boolean historyDisabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Is the history list persisted to file.
     * Set to true by default
     *
     * @return is history persistent
     */
    @Override
    public boolean historyPersistent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Is the history list persisted to file.
     * Set to true by default
     *
     * @param historyPersistent history
     */
    public void setHistoryPersistent(boolean historyPersistent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAliasFile(File file) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public File aliasFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean aliasEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAliasEnabled(boolean enabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setPersistAlias(boolean persist) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean persistAlias() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AliasManager aliasManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAliasManager(AliasManager aliasManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setQuitHandler(QuitHandler qh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public QuitHandler quitHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void enableOperatorParser(boolean enable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean operatorParserEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean manEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setManEnabled(boolean enabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AeshContext aeshContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAeshContext(AeshContext aeshContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public File exportFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setExportFile(File exportFile) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean exportEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setExportEnabled(boolean exportEnabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setPersistExport(boolean persistExport) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean persistExport() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setExportUsesSystemEnvironment(boolean isLoad) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean exportUsesSystemEnvironment() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setResource(Resource resource) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setExecuteAtStart(String execute) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String executeAtStart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setExecuteFileAtStart(Resource executeFileAtStart) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource executeFileAtStart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource resource() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandRegistry<CI> commandRegistry() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandInvocationProvider<CI> commandInvocationProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandNotFoundHandler commandNotFoundHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CompleterInvocationProvider completerInvocationProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ConverterInvocationProvider converterInvocationProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ValidatorInvocationProvider validatorInvocationProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OptionActivatorProvider optionActivatorProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ManProvider manProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandActivatorProvider commandActivatorProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Connection connection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCommandActivatorProvider(CommandActivatorProvider commandActivatorProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setOptionActivatorProvider(OptionActivatorProvider optionActivatorProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCommandRegistry(CommandRegistry<CI> commandRegistry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCommandInvocationProvider(CommandInvocationProvider<CI> commandInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCommandNotFoundHandler(CommandNotFoundHandler commandNotFoundHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCompleterInvocationProvider(CompleterInvocationProvider completerInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setConverterInvocationProvider(ConverterInvocationProvider converterInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setValidatorInvocationProvider(ValidatorInvocationProvider validatorInvocationProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setManProvider(ManProvider manProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Object clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public InvocationProviders invocationProviders() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ExportChangeListener exportListener() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setExportListener(ExportChangeListener listener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setConnection(Connection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setInvocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void echoCtrl(boolean echoCtrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEchoCtrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setInterruptHandler(Consumer<Void> interruptHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<Void> getInterruptHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setConnectionClosedHandler(Consumer<Void> handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<Void> connectionClosedHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void redrawPromptOnInterrupt(boolean redrawPrompt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isRedrawPromptOnInterrupt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setScanForCommandPackages(String... packages) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getScanForCommandPackages() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setEnableSearchInPaging(boolean enable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enableSearchInPaging() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SubCommandModeSettings subCommandModeSettings() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setSubCommandModeSettings(SubCommandModeSettings subCommandModeSettings) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
