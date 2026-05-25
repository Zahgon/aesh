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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aesh.command.Command;
import org.aesh.command.DefaultValueProvider;
import org.aesh.command.HelpSectionProvider;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.impl.parser.CompleteStatus;
import org.aesh.command.impl.populator.AeshCommandPopulator;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.parser.OptionParserException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.result.ResultHandler;
import org.aesh.command.validator.CommandValidator;
import org.aesh.selector.SelectorType;
import org.aesh.terminal.formatting.TerminalString;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Config;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class ProcessedCommand<C extends Command<CI>, CI extends CommandInvocation> {

    private static final Pattern DESCRIPTION_VARIABLE_PATTERN = Pattern.compile("\\$\\{([^}]+)}");

    private final String name;

    private final String description;

    private final CommandValidator<C, CI> validator;

    private final ResultHandler resultHandler;

    private final CommandPopulator<Object, CI> populator;

    private final boolean disableParsing;

    private final boolean stopAtFirstPositional;

    private final boolean sortOptions;

    private DefaultValueProvider defaultValueProvider;

    private CommandActivator activator;

    private final boolean generateHelp;

    private String version;

    private String helpUrl;

    private String helpGroup = "";

    private Class<? extends HelpSectionProvider> helpSectionProviderClass;

    private HelpSectionProvider helpSectionProvider;

    private List<ProcessedOption> options;

    private ProcessedOption arguments;

    private ProcessedOption argument;

    private final List<ProcessedOption> argumentOptions;

    private final C command;

    private final List<String> aliases;

    private List<CommandLineParserException> parserExceptions;

    private CompleteStatus completeStatus;

    private java.util.function.BiConsumer<Object, Object> parentCommandInjector;

    private int optionDeclarationCounter;

    public ProcessedCommand(String name, List<String> aliases, C command, String description, CommandValidator<C, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, CommandActivator activator) throws OptionParserException {
        this(name, aliases, command, description, validator, resultHandler, generateHelp, disableParsing, version, arguments, options, argument, populator, activator, null);
    }

    public ProcessedCommand(String name, List<String> aliases, C command, String description, CommandValidator<C, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, CommandActivator activator, String helpUrl) throws OptionParserException {
        this(name, aliases, command, description, validator, resultHandler, generateHelp, disableParsing, version, arguments, options, argument, populator, activator, helpUrl, false);
    }

    public ProcessedCommand(String name, List<String> aliases, C command, String description, CommandValidator<C, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, CommandActivator activator, String helpUrl, boolean stopAtFirstPositional) throws OptionParserException {
        this(name, aliases, command, description, validator, resultHandler, generateHelp, disableParsing, version, arguments, options, argument, populator, activator, helpUrl, stopAtFirstPositional, null);
    }

    public ProcessedCommand(String name, List<String> aliases, C command, String description, CommandValidator<C, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, CommandActivator activator, String helpUrl, boolean stopAtFirstPositional, DefaultValueProvider defaultValueProvider) throws OptionParserException {
        this(name, aliases, command, description, validator, resultHandler, generateHelp, disableParsing, version, arguments, options, argument, populator, activator, helpUrl, stopAtFirstPositional, defaultValueProvider, false);
    }

    public ProcessedCommand(String name, List<String> aliases, C command, String description, CommandValidator<C, CI> validator, ResultHandler resultHandler, boolean generateHelp, boolean disableParsing, String version, ProcessedOption arguments, List<ProcessedOption> options, ProcessedOption argument, CommandPopulator<Object, CI> populator, CommandActivator activator, String helpUrl, boolean stopAtFirstPositional, DefaultValueProvider defaultValueProvider, boolean sortOptions) throws OptionParserException {
        this.name = name;
        this.description = description;
        this.aliases = aliases == null ? Collections.emptyList() : aliases;
        this.validator = validator;
        this.generateHelp = generateHelp;
        this.disableParsing = disableParsing;
        this.stopAtFirstPositional = stopAtFirstPositional;
        this.sortOptions = sortOptions;
        this.defaultValueProvider = defaultValueProvider;
        this.helpUrl = helpUrl;
        this.resultHandler = resultHandler;
        this.arguments = arguments;
        this.argument = argument;
        this.argumentOptions = new ArrayList<>(1);
        if (argument != null)
            this.argumentOptions.add(argument);
        this.options = new ArrayList<>(options.size() + (generateHelp ? 1 : 0) + (version != null && version.length() > 0 ? 1 : 0));
        this.optionDeclarationCounter = 0;
        this.command = command;
        this.activator = activator;
        if (populator == null)
            this.populator = new AeshCommandPopulator<>(this.command);
        else
            this.populator = populator;
        setOptions(options);
        if (generateHelp)
            doGenerateHelp();
        if (version != null && version.length() > 0) {
            this.version = version;
            doGenerateVersion();
        }
        parserExceptions = Collections.emptyList();
        // Capture initial field values for arguments/argument set before command
        if (command != null) {
            if (this.arguments != null)
                this.arguments.captureInitialValue(command);
            for (ProcessedOption argOpt : this.argumentOptions) argOpt.captureInitialValue(command);
        }
    }

    public List<ProcessedOption> getOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CommandActivator getActivator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isActivated(ParsedCommand parsedCommand) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getAliases() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addOption(ProcessedOption opt) throws OptionParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Add an option without verifying name uniqueness. Use only from generated
     * (annotation-processor) code where names are validated at compile time.
     * Skipping the O(N) scan per option eliminates O(N^2) overhead during
     * command registration.
     */
    public void addOptionDirect(ProcessedOption opt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getDisplayOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void setOptions(List<ProcessedOption> options) throws OptionParserException {
        for (ProcessedOption opt : options) {
            addOption(opt);
        }
    }

    public String name() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String description() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CommandValidator<C, CI> validator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ResultHandler resultHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasArguments() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption getArguments() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setArguments(ProcessedOption arguments) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getArgumentOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addArgument(ProcessedOption arg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CommandPopulator<Object, CI> getCommandPopulator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public C getCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean generateHelp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean disableParsing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean stopAtFirstPositional() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean sortOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DefaultValueProvider getDefaultValueProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the default value provider. Used by the registry to inject a
     * registry-level fallback when the command has no per-command provider.
     */
    public void setDefaultValueProvider(DefaultValueProvider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String version() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String helpUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String helpGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHelpGroup(String helpGroup) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Class<? extends HelpSectionProvider> getHelpSectionProviderClass() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHelpSectionProviderClass(Class<? extends HelpSectionProvider> helpSectionProviderClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public HelpSectionProvider getHelpSectionProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHelpSectionProvider(HelpSectionProvider helpSectionProvider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setParentCommandInjector(java.util.function.BiConsumer<Object, Object> injector) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public java.util.function.BiConsumer<Object, Object> getParentCommandInjector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private char verifyThatNamesAreUnique(String name, String longName) throws OptionParserException {
        if (name != null)
            return verifyThatNamesAreUnique(name.charAt(0), longName);
        else
            return verifyThatNamesAreUnique('\u0000', longName);
    }

    private char verifyThatNamesAreUnique(char name, String longName) throws OptionParserException {
        if (longName != null && longName.length() > 0 && findLongOption(longName) != null) {
            throw new OptionParserException("Option --" + longName + " is already added to Param: " + this.toString());
        }
        if (name != '\u0000' && findOption(String.valueOf(name)) != null) {
            throw new OptionParserException("Option -" + name + " is already added to Param: " + this.toString());
        }
        //if name is null, use one based on name
        if (name == '\u0000' && (longName == null || longName.length() == 0))
            throw new OptionParserException("Neither option name and option long name can be both null");
        return name;
    }

    public ProcessedOption findOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption findOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Generic search for matching options with input that start with either -- or -
     *
     * @param input input
     * @return matching option
     */
    public ProcessedOption searchAllOptions(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption findLongOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption findLongOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Find an option by its negated name (e.g., "no-verbose" for option "verbose").
     * If found, marks the option as negated.
     *
     * @param name the negated name to search for
     * @return the matching option, or null if not found
     */
    public ProcessedOption findNegatedOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Find an option by its negated name without checking activator.
     *
     * @param name the negated name to search for
     * @return the matching option, or null if not found
     */
    public ProcessedOption findNegatedOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption findBareLongOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<TerminalString> findPossibleBareLongNamesWithDash(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption startWithOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption startWithLongOption(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption startWithLongOptionNoActivatorCheck(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static boolean startsWithAlias(ProcessedOption option, String name) {
        for (String alias : option.getAliases()) {
            if (name.startsWith(alias))
                return true;
        }
        return false;
    }

    private static int startsWithNameOrAlias(ProcessedOption option, String name) {
        int longest = -1;
        if (name.startsWith(option.name()))
            longest = option.name().length();
        for (String alias : option.getAliases()) {
            if (name.startsWith(alias) && alias.length() > longest)
                longest = alias.length();
        }
        return longest;
    }

    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void clearOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void doGenerateHelp() {
        //only generate a help option if there is no other option already called help
        if (findOption("help") == null) {
            try {
                ProcessedOption helpOption = ProcessedOptionBuilder.builder().name("help").shortName('h').description("Display this help and exit").required(false).optionType(OptionType.BOOLEAN).type(Boolean.class).hasValue(false).overrideRequired(true).fieldName("generatedHelp").build();
                helpOption.setDeclarationOrder(optionDeclarationCounter++);
                options.add(helpOption);
                helpOption.setParent(this);
            } catch (OptionParserException e) {
                throw new RuntimeException("Failed to generate help option", e);
            }
        }
    }

    public boolean isGenerateHelpOptionSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isFullHelpRequested() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void doGenerateVersion() {
        //only generate a version option if there is no other option already called version
        if (findOption("version") == null) {
            try {
                ProcessedOption versionOption = ProcessedOptionBuilder.builder().name("version").shortName('v').description("Displays version information of the command").hasValue(false).required(false).optionType(OptionType.BOOLEAN).type(Boolean.class).overrideRequired(true).fieldName("generatedVersion").build();
                versionOption.setDeclarationOrder(optionDeclarationCounter++);
                options.add(versionOption);
                versionOption.setParent(this);
            } catch (OptionParserException e) {
                throw new RuntimeException("Failed to generate version option", e);
            }
        }
    }

    public boolean isGenerateVersionOptionSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return all option names that not already have a value
     * and is enabled. For negatable options, also includes the negated form.
     */
    public List<TerminalString> getOptionLongNamesWithDash() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<TerminalString> findPossibleLongNamesWithDash(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean isExcludedBySetOption(ProcessedOption option) {
        if (option.getExclusiveWith().isEmpty())
            return false;
        for (String exclusiveName : option.getExclusiveWith()) {
            ProcessedOption other = findLongOptionNoActivatorCheck(exclusiveName);
            if (other != null && other.getValue() != null)
                return true;
        }
        return false;
    }

    public List<String> findPossibleLongNames(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return returns true if the command has any options with askIfNotSet to true
     *         and its value is not set.
     */
    public boolean hasAskIfNotSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getAllAskIfNotSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a description String based on the defined command and options.
     * Useful when printing "help" info etc.
     */
    public String printHelp(String commandName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String printHelp(String commandName, boolean supportsHyperlinks) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a description String based on the defined command and options.
     * Useful when printing "help" info etc.
     *
     * @param commandName the command name to display
     * @param supportsHyperlinks whether the terminal supports OSC 8 hyperlinks
     * @param showAll when true, includes FULL visibility options in output
     */
    public String printHelp(String commandName, boolean supportsHyperlinks, boolean showAll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wrap the synopsis line at the given width, indenting continuation lines
     * to align with the first option (after "Usage: commandname ").
     */
    private static String wrapSynopsis(String prefix, String options, int width) {
        // Calculate visual length excluding ANSI escape sequences
        int visualPrefixLen = prefix.replaceAll("\u001B\\[[;\\d]*m", "").length();
        String full = prefix + options;
        String visualFull = full.replaceAll("\u001B\\[[;\\d]*m", "");
        if (visualFull.length() <= width)
            return full;
        int indent = visualPrefixLen + 1;
        String pad = String.format("%" + indent + "s", "");
        StringBuilder result = new StringBuilder();
        result.append(prefix);
        int currentLineLen = visualPrefixLen;
        // Split on spaces but keep the tokens (option groups like "[--foo]")
        // Note: do NOT use trim() — it strips chars <= 0x20 which includes ESC (0x1B)
        String trimmed = options;
        while (trimmed.startsWith(" ")) trimmed = trimmed.substring(1);
        String[] tokens = trimmed.split(" +");
        for (String token : tokens) {
            int visualTokenLen = token.replaceAll("\u001B\\[[;\\d]*m", "").length();
            if (currentLineLen + 1 + visualTokenLen > width && currentLineLen > indent) {
                result.append(Config.getLineSeparator()).append(pad);
                currentLineLen = indent;
            }
            result.append(" ").append(token);
            currentLineLen += 1 + visualTokenLen;
        }
        return result.toString();
    }

    /**
     * Build a detailed synopsis string from visible options.
     * Groups boolean short flags together [-hVx], shows value options individually
     * [--config=<config>], renders mutually exclusive options with pipes
     * [--verbose | --quiet], and shows required options without brackets.
     */
    private String buildDetailedSynopsis(List<ProcessedOption> visibleOpts) {
        if (visibleOpts.isEmpty())
            return "";
        // Determine ansiMode from the first option
        boolean ansi = visibleOpts.get(0).isAnsiMode();
        // Collect mutually exclusive groups to avoid showing them individually
        java.util.Set<String> exclusiveHandled = new java.util.HashSet<>();
        // 1. Group boolean short flags (exclude negatable — they need --[no-]name format)
        StringBuilder shortFlags = new StringBuilder();
        for (ProcessedOption o : visibleOpts) {
            if (o.getOptionType() == OptionType.BOOLEAN && o.shortName() != null && !o.isRequired() && o.getExclusiveWith().isEmpty() && !o.isNegatable()) {
                shortFlags.append(o.shortName());
            }
        }
        StringBuilder synopsis = new StringBuilder();
        // Emit grouped short flags: [-hVx]
        if (shortFlags.length() > 0) {
            if (ansi)
                synopsis.append(" ").append(ANSI.YELLOW_TEXT).append("[-").append(shortFlags).append("]").append(ANSI.RESET);
            else
                synopsis.append(" [-").append(shortFlags).append("]");
        }
        // 2. Emit remaining options
        for (ProcessedOption o : visibleOpts) {
            // Skip boolean short flags already grouped (but not negatable ones)
            if (o.getOptionType() == OptionType.BOOLEAN && o.shortName() != null && !o.isRequired() && o.getExclusiveWith().isEmpty() && !o.isNegatable()) {
                continue;
            }
            // For negatable options, use --[no-]name format
            String optName;
            if (o.isNegatable()) {
                optName = "--[" + o.getNegationPrefix() + "]" + o.name();
            } else {
                optName = o.shortName() != null ? "-" + o.shortName() : "--" + o.name();
            }
            // Apply yellow styling to option name
            String styledOptName = ansi ? ANSI.YELLOW_TEXT + optName + ANSI.RESET : optName;
            // Handle mutually exclusive options
            if (!o.getExclusiveWith().isEmpty() && !exclusiveHandled.contains(o.name())) {
                StringBuilder exclusive = new StringBuilder();
                exclusive.append(styledOptName);
                for (String exName : o.getExclusiveWith()) {
                    ProcessedOption exOpt = findLongOptionNoActivatorCheck(exName);
                    if (exOpt != null) {
                        String exOptName = exOpt.shortName() != null ? "-" + exOpt.shortName() : "--" + exOpt.name();
                        String styledEx = ansi ? ANSI.YELLOW_TEXT + exOptName + ANSI.RESET : exOptName;
                        exclusive.append(" | ").append(styledEx);
                        exclusiveHandled.add(exOpt.name());
                    }
                }
                exclusiveHandled.add(o.name());
                if (o.isRequired()) {
                    synopsis.append(" (").append(exclusive).append(")");
                } else {
                    synopsis.append(" [").append(exclusive).append("]");
                }
                continue;
            }
            if (exclusiveHandled.contains(o.name()))
                continue;
            // Regular option
            String rendered = styledOptName;
            if (o.getOptionType() == OptionType.GROUP) {
                String placeholder = ansi ? ANSI.CYAN_TEXT + "<key>=<value>" + ANSI.RESET : "<key>=<value>";
                rendered = styledOptName + placeholder;
            } else if (o.hasValue() && o.getOptionType() != OptionType.BOOLEAN && (o.type() != Boolean.class && o.type() != boolean.class) && !o.isOptionalValue() && !o.hasFallbackValue()) {
                String placeholder = o.getArgument() != null && !o.getArgument().isEmpty() ? o.getArgument() : o.name();
                String styledPlaceholder = ansi ? ANSI.CYAN_TEXT + "=<" + placeholder + ">" + ANSI.RESET : "=<" + placeholder + ">";
                rendered = styledOptName + styledPlaceholder;
            }
            if (o.isRequired()) {
                synopsis.append(" ").append(rendered);
            } else {
                synopsis.append(" [").append(rendered).append("]");
            }
        }
        return synopsis.toString();
    }

    private String formatArgumentSynopsis(ProcessedOption arg) {
        String label = arg.isTypeAssignableByResourcesOrFile() ? (arg.getOptionType() == OptionType.ARGUMENTS ? "files" : "file") : arg.getDisplayLabel();
        org.aesh.command.option.Arity arity = arg.getArity();
        if (arity != null) {
            StringBuilder sb = new StringBuilder();
            boolean optional = arity.getMin() == 0;
            sb.append(optional ? " [" : " ");
            // Show repeated labels for small fixed arities
            if (arity.getMax() == arity.getMin() && arity.getMax() <= 3) {
                for (int i = 0; i < arity.getMax(); i++) {
                    if (i > 0)
                        sb.append(" ");
                    sb.append("<").append(label).append(">");
                }
            } else {
                sb.append("<").append(label).append(">");
                if (arity.getMax() > 1 || arity.isUnlimited())
                    sb.append("...");
            }
            if (optional)
                sb.append("]");
            return sb.toString();
        }
        // Legacy behavior when no arity is set
        if (arg.getOptionType() == OptionType.ARGUMENTS)
            return " [<" + label + ">]";
        else
            return " <" + label + ">";
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean anyOptionsSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasLongOption(String optionName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    //will only return true if the optionName equals an option and it does
    //not start with another option name
    public boolean hasUniqueLongOption(String optionName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void updateInvocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateOptionsInvocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addParserException(CommandLineParserException exception) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<CommandLineParserException> parserExceptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasOptionsWithInjectedValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasOptionWithOverrideRequired() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CompleteStatus completeStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCompleteStatus(CompleteStatus completeStatus) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setArgument(ProcessedOption arg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption getArgument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasArgument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasArgumentWithNoValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getPositionalValueCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption getPositionalForIndex(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption getPositionalForNextValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String positionalRangeSummary() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getPositionalOptionsInDisplayOrder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String resolveCommandDescription(String description, String commandName, String fullCommandName, String rootCommandName, String parentCommandName, String parentCommandFullName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String resolveOptionDescription(ProcessedOption option, String commandName, String fullCommandName, String rootCommandName, String parentCommandName, String parentCommandFullName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final class DescriptionResolver {

        private final String commandName;

        private final String commandFullName;

        private final String rootCommandName;

        private final String parentCommandName;

        private final String parentCommandFullName;

        private DescriptionResolver(String commandName, String commandFullName, String rootCommandName, String parentCommandName, String parentCommandFullName) {
            String fullName = commandFullName != null ? commandFullName : commandName;
            this.commandFullName = fullName;
            this.commandName = commandName != null ? commandName : lastToken(fullName);
            this.rootCommandName = rootCommandName != null ? rootCommandName : firstToken(fullName);
            this.parentCommandFullName = parentCommandFullName != null ? parentCommandFullName : parentPath(fullName);
            this.parentCommandName = parentCommandName != null ? parentCommandName : lastToken(this.parentCommandFullName);
        }

        private String resolveCommandDescription(String raw) {
            return resolve(raw, null);
        }

        private String resolveOptionDescription(ProcessedOption option) {
            return resolve(option != null ? option.description() : null, option);
        }

        private String resolve(String raw, ProcessedOption option) {
            if (raw == null || raw.isEmpty())
                return raw;
            Matcher matcher = DESCRIPTION_VARIABLE_PATTERN.matcher(raw);
            StringBuffer out = new StringBuffer(raw.length());
            while (matcher.find()) {
                String key = matcher.group(1);
                String replacement = resolveVariable(key, option);
                if (replacement == null)
                    replacement = matcher.group(0);
                matcher.appendReplacement(out, Matcher.quoteReplacement(replacement));
            }
            matcher.appendTail(out);
            return out.toString();
        }

        private String resolveVariable(String key, ProcessedOption option) {
            switch(key) {
                case "COMMAND-NAME":
                    return commandName;
                case "COMMAND-FULL-NAME":
                    return commandFullName;
                case "ROOT-COMMAND-NAME":
                    return rootCommandName;
                case "PARENT-COMMAND-NAME":
                    return parentCommandName;
                case "PARENT-COMMAND-FULL-NAME":
                    return parentCommandFullName;
                case "DEFAULT-VALUE":
                case "FALLBACK-VALUE":
                    return optionDefaultValue(option);
                case "COMPLETION-CANDIDATES":
                    return optionCompletionCandidates(option);
                default:
                    return null;
            }
        }

        private static String optionDefaultValue(ProcessedOption option) {
            if (option == null || option.getDefaultValues().isEmpty())
                return "";
            return String.join(", ", option.getDefaultValues());
        }

        private static String optionCompletionCandidates(ProcessedOption option) {
            if (option == null)
                return "";
            List<String> candidates = option.getAllowedValues();
            if (candidates == null || candidates.isEmpty())
                return "";
            return String.join(", ", candidates);
        }

        private static String firstToken(String value) {
            if (value == null)
                return null;
            int idx = value.indexOf(' ');
            return idx < 0 ? value : value.substring(0, idx);
        }

        private static String lastToken(String value) {
            if (value == null)
                return null;
            int idx = value.lastIndexOf(' ');
            return idx < 0 ? value : value.substring(idx + 1);
        }

        private static String parentPath(String value) {
            if (value == null)
                return null;
            int idx = value.lastIndexOf(' ');
            if (idx < 0)
                return null;
            return value.substring(0, idx);
        }
    }

    public boolean hasArgumentsWithNoValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasSelector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<ProcessedOption> getAllSelectors() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
