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
package org.aesh.command.impl.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.aesh.command.Command;
import org.aesh.command.CommandLifecycle;
import org.aesh.command.HelpEntry;
import org.aesh.command.HelpSectionProvider;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.impl.container.AeshCommandContainerBuilder;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.provider.NullHelpSectionProvider;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.map.MapCommand;
import org.aesh.command.map.MapCommandPopulator;
import org.aesh.command.map.MapProcessedCommand;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.parser.MutuallyExclusiveOptionException;
import org.aesh.command.parser.OptionParserException;
import org.aesh.command.parser.RequiredOptionException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.complete.AeshCompleteOperation;
import org.aesh.console.AeshContext;
import org.aesh.parser.LineParser;
import org.aesh.parser.ParsedLine;
import org.aesh.parser.ParsedLineIterator;
import org.aesh.parser.ParsedWord;
import org.aesh.parser.ParserStatus;
import org.aesh.terminal.utils.ANSIBuilder;
import org.aesh.terminal.utils.Config;

/**
 * A simple command line parser.
 * It parses a given string based on the Command given and
 *
 * It can also print a formatted usage/help information.
 *
 * @author Aesh team
 */
public class AeshCommandLineParser<CI extends CommandInvocation> implements CommandLineParser<CI> {

    private final ProcessedCommand<Command<CI>, CI> processedCommand;

    private List<CommandLineParser<CI>> childParsers;

    private Map<String, Class<? extends Command>> lazyChildClasses;

    private InvocationProviders storedInvocationProviders;

    private boolean isChild = false;

    private ProcessedOption lastParsedOption;

    private boolean parsedCommand = false;

    private LineParser lineParser;

    private CompleteStatus completeStatus;

    private AeshCommandLineParser<CI> parent;

    private boolean ansiMode = true;

    @SuppressWarnings("unchecked")
    public AeshCommandLineParser(ProcessedCommand<? extends Command<CI>, CI> processedCommand) {
        this.processedCommand = (ProcessedCommand<Command<CI>, CI>) processedCommand;
    }

    @Override
    public void addChildParser(CommandLineParser<CI> commandLineParser) throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<CommandLineParser<CI>> getChildParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public void addLazyChild(String name, Class<? extends Command> clazz) throws CommandLineParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void storeInvocationProviders(InvocationProviders providers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private void resolveAllLazyChildren() {
        if (lazyChildClasses == null || lazyChildClasses.isEmpty())
            return;
        AeshCommandContainerBuilder<CI> builder = new AeshCommandContainerBuilder<>();
        for (Class<? extends Command> clazz : new ArrayList<>(lazyChildClasses.values())) {
            try {
                CommandContainer<CI> container = builder.create(clazz);
                addChildParser(container.getParser());
                applyStoredProviders(container.getParser());
            } catch (CommandLineParserException e) {
                // best-effort: skip unresolvable children
            }
        }
        lazyChildClasses.clear();
    }

    @SuppressWarnings("unchecked")
    private CommandLineParser<CI> resolveLazyChild(String name) {
        if (lazyChildClasses == null)
            return null;
        Class<? extends Command> clazz = lazyChildClasses.remove(name);
        if (clazz == null)
            return null;
        try {
            AeshCommandContainerBuilder<CI> builder = new AeshCommandContainerBuilder<>();
            CommandContainer<CI> container = builder.create(clazz);
            addChildParser(container.getParser());
            applyStoredProviders(container.getParser());
            return container.getParser();
        } catch (CommandLineParserException e) {
            return null;
        }
    }

    private void applyStoredProviders(CommandLineParser<CI> child) {
        if (storedInvocationProviders != null) {
            child.getProcessedCommand().updateInvocationProviders(storedInvocationProviders);
        }
    }

    @Override
    public void setChild(boolean child) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void setParent(AeshCommandLineParser<CI> parent) {
        this.parent = parent;
    }

    AeshCommandLineParser<CI> getParentParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandLineParser<CI> parsedCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void complete(AeshCompleteOperation completeOperation, InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void complete(AeshCompleteOperation completeOperation, ParsedLine line, InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> getAllNames() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isChild() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandLineParser<CI> getChildParser(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<CommandLineParser<CI>> getAllChildParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProcessedCommand<Command<CI>, CI> getProcessedCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Command<CI> getCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandLineCompletionParser getCompletionParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CommandPopulator<Object, CI> getCommandPopulator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void populateObject(String line, InvocationProviders invocationProviders, AeshContext aeshContext, Mode mode) throws CommandLineParserException, OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void doPopulate(ProcessedCommand<Command<CI>, CI> processedCommand, InvocationProviders invocationProviders, AeshContext aeshContext, Mode mode) throws CommandLineParserException, OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a usage String based on the defined command and options.
     * Useful when printing "help" info etc.
     */
    @Override
    public String printHelp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private HelpSectionProvider resolveHelpSectionProvider() {
        HelpSectionProvider provider = processedCommand.getHelpSectionProvider();
        if (provider != null)
            return provider;
        Class<? extends HelpSectionProvider> providerClass = processedCommand.getHelpSectionProviderClass();
        if (providerClass != null && providerClass != NullHelpSectionProvider.class) {
            try {
                return providerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                // fall through
            }
        }
        return null;
    }

    private String formatHelpEntry(HelpEntry entry, int offset, int descriptionStart) {
        ANSIBuilder ansiBuilder = ANSIBuilder.builder(ansiMode);
        if (offset > 0)
            ansiBuilder.append(String.format("%" + offset + "s", ""));
        ansiBuilder.blueText(entry.name());
        int descOffset = descriptionStart - entry.name().length();
        if (descOffset > 0)
            ansiBuilder.append(String.format("%" + descOffset + "s", ""));
        else
            ansiBuilder.append(" ");
        if (entry.description() != null)
            ansiBuilder.append(resolveDescriptionVariables(entry.description(), null));
        return ansiBuilder.toString();
    }

    private String helpNames() {
        if (isChild()) {
            return parent.helpNames() + " " + processedCommand.name();
        }
        return processedCommand.name();
    }

    /**
     * Parse a command line with the defined command as base of the rules.
     * If any options are found, but not defined in the command object an
     * CommandLineParserException will be thrown.
     * Also, if a required option is not found or options specified with value,
     * but is not given any value an OptionParserException will be thrown.
     *
     * @param line input
     */
    @Override
    public void parse(String line) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void parse(ParsedLineIterator iterator, Mode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void doParse(ParsedLineIterator iter, Mode mode) {
        parsedCommand = true;
        if (mode == Mode.COMPLETION)
            doParseCompletion(iter);
        else {
            try {
                boolean argumentMarker = false;
                while (iter.hasNextWord()) {
                    ParsedWord word = iter.peekParsedWord();
                    if (argumentMarker || processedCommand.disableParsing()) {
                        setArgStatus(word.word());
                        iter.pollParsedWord();
                    } else {
                        lastParsedOption = processedCommand.searchAllOptions(word.word());
                        if (lastParsedOption == null && parent != null) {
                            lastParsedOption = searchParentInheritedOption(word.word());
                        }
                        if (lastParsedOption != null) {
                            lastParsedOption.parser().parse(iter, lastParsedOption);
                        } else {
                            //if we have a -- and its not at the end of the line it is used as a
                            //marker to signal that all the values after it are arguments, so we will ignore this
                            if (word.word().equals("--") && !iter.isNextWordCursorWord()) {
                                argumentMarker = true;
                            } else {
                                // Unknown commands are possible with a dynamic command (MapCommand)
                                // In this case we shouldn't validate the option and pass it down to
                                // the populator for Map injection.
                                boolean unknown = false;
                                if (!processedCommand.stopAtFirstPositional() && word.word().startsWith("-")) {
                                    if (word.word().startsWith("--") || word.word().length() == 2) {
                                        // invalid short names and long names should be rejected.
                                        if (!(processedCommand.getCommand() instanceof MapCommand)) {
                                            processedCommand.addParserException(new OptionParserException("The option " + word.word() + " is unknown."));
                                        } else {
                                            unknown = true;
                                        }
                                    }
                                }
                                if (unknown) {
                                    // Pass down the option directly to the populator.
                                    MapCommandPopulator pop = (MapCommandPopulator) processedCommand.getCommandPopulator();
                                    pop.addUnknownOption(word.word());
                                } else if (isGroupCommand()) {
                                    // Check if this word is a subcommand name.
                                    // When group command options are parsed before the subcommand,
                                    // e.g. "cli -c cliarg command -c commandarg", we need to
                                    // recognize "command" as a subcommand and delegate parsing.
                                    CommandLineParser<CI> clp = getChildParser(word.word());
                                    if (clp != null) {
                                        // Do NOT poll the word - child's parse() expects the
                                        // command name to still be in the iterator.
                                        // Reset parsedCommand so parsedCommand() returns the child, not us.
                                        parsedCommand = false;
                                        clp.parse(iter, mode);
                                        return;
                                    } else {
                                        setArgStatus(word.word());
                                        if (processedCommand.stopAtFirstPositional())
                                            argumentMarker = true;
                                    }
                                } else {
                                    setArgStatus(word.word());
                                    if (processedCommand.stopAtFirstPositional())
                                        argumentMarker = true;
                                }
                            }
                            iter.pollParsedWord();
                        }
                    }
                }
            } catch (OptionParserException ope) {
                processedCommand.addParserException(ope);
            }
            if (mode == Mode.STRICT) {
                ProcessedCommand copy = processedCommand;
                if (copy instanceof MapProcessedCommand) {
                    MapCommand mc = (MapCommand) copy.getCommand();
                    if (!mc.checkForRequiredOptions(iter.baseLine())) {
                        return;
                    }
                }
                RequiredOptionException re = checkForMissingRequiredOptions(processedCommand);
                if (re != null)
                    processedCommand.addParserException(re);
            }
            if (mode == Mode.STRICT || mode == Mode.VALIDATE) {
                MutuallyExclusiveOptionException me = checkForMutuallyExclusiveOptions(processedCommand);
                if (me != null)
                    processedCommand.addParserException(me);
                // Check arity min constraints on arguments (skip if overrideRequired is active)
                if (!processedCommand.hasOptionWithOverrideRequired()) {
                    RequiredOptionException arityEx = null;
                    for (ProcessedOption argOpt : processedCommand.getArgumentOptions()) {
                        arityEx = checkArityMin(argOpt);
                        if (arityEx != null) {
                            processedCommand.addParserException(arityEx);
                            break;
                        }
                    }
                    arityEx = checkArityMin(processedCommand.getArguments());
                    if (arityEx != null)
                        processedCommand.addParserException(arityEx);
                }
            }
        }
    }

    private void setArgStatus(String word) {
        ProcessedOption positional = processedCommand.getPositionalForNextValue();
        if (positional != null) {
            if (positional.isArityFull()) {
                processedCommand.addParserException(new OptionParserException("Too many arguments. Maximum is " + positional.getArity().getMax() + "."));
            } else {
                positional.addValue(word);
            }
        } else {
            int missingIndex = processedCommand.getPositionalValueCount();
            processedCommand.addParserException(new OptionParserException("Unexpected positional value '" + word + "' at index " + missingIndex + ". Declared positional indexes: " + processedCommand.positionalRangeSummary() + "."));
        }
    }

    private void doParseCompletion(ParsedLineIterator iter) {
        ProcessedCommand copy = processedCommand;
        if (copy instanceof MapProcessedCommand) {
            ((MapProcessedCommand) copy).setMode(Mode.COMPLETION);
        }
        if (!iter.hasNextWord()) {
            if (isGroupCommand())
                processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.GROUP_COMMAND, ""));
            else {
                //child commands that ends after its name, must be able to append space
                if (iter.baseLine().size() == (iter.baseLine().selectedIndex() + 1) && lastParsedOption == null) {
                    //append space
                    if (iter.baseLine().status() == ParserStatus.OK)
                        processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.APPEND_SPACE, ""));
                    else
                        //we have unclosed quote, lets parse it as an argument
                        processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.ARGUMENT, ""));
                } else
                    //we list all the options
                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.COMPLETE_OPTION, ""));
            }
        } else {
            try {
                //argumentMarker is set to true if we have found "--" inside the line
                boolean argumentMarker = false;
                while (iter.hasNextWord()) {
                    //first check if we have passed the selected word, if so lets stop
                    if (iter.baseLine().selectedIndex() > -1 && iter.pastCursorWord() && processedCommand.completeStatus() != null)
                        return;
                    ParsedWord word = iter.peekParsedWord();
                    //first check if argumentMarker has been set
                    if (argumentMarker) {
                        setCompletionArgStatus(word.word());
                        iter.pollParsedWord();
                    } else {
                        lastParsedOption = processedCommand.searchAllOptions(word.word());
                        if (lastParsedOption == null && parent != null) {
                            lastParsedOption = searchParentInheritedOption(word.word());
                        }
                        if (lastParsedOption != null) {
                            //if current word is cursor word, we need to check if the current option name
                            //might be part of another option name: eg: list and listFolders
                            if (iter.isNextWordCursorWord() && !word.word().contains("=") && processedCommand.findPossibleLongNames(word.word()).size() > 1) {
                                processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.LONG_OPTION, word.word().substring(2)));
                                iter.pollParsedWord();
                            } else {
                                lastParsedOption.parser().parse(iter, lastParsedOption);
                                if (!iter.hasNextWord()) {
                                    if (lastParsedOption.hasValue() || iter.baseLine().spaceAtEnd())
                                        processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.COMPLETE_OPTION, ""));
                                    else
                                        //if the option do not have any value, set missing value status for easier processing
                                        processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.OPTION_MISSING_VALUE, ""));
                                }
                            }
                        } else //if we have -- that stands alone it's a marker for separation of options and arguments
                        if (word.word().equals("--") && !iter.isNextWordCursorWord()) {
                            argumentMarker = true;
                            iter.pollParsedWord();
                        } else //got a partial option
                        if (word.word().startsWith("--")) {
                            processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.LONG_OPTION, word.word().substring(2)));
                            iter.pollParsedWord();
                        } else if (word.word().startsWith("-")) {
                            processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.SHORT_OPTION, word.word().substring(1)));
                            iter.pollParsedWord();
                        } else //we're completing arguments or group command names
                        {
                            //check for group command completion even after options have been parsed
                            if (isGroupCommand()) {
                                // Check if this word matches a known child parser
                                CommandLineParser<CI> clp = getChildParser(word.word());
                                if (clp != null && !iter.isNextWordCursorWord()) {
                                    // Fully matched subcommand, delegate to it
                                    parsedCommand = false;
                                    clp.parse(iter, Mode.COMPLETION);
                                    return;
                                }
                                if (iter.isNextWordCursorWord())
                                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.GROUP_COMMAND, word.word()));
                                else if (iter.baseLine().cursorAtEnd() && iter.baseLine().spaceAtEnd())
                                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.GROUP_COMMAND, ""));
                                else if (processedCommand.stopAtFirstPositional())
                                    argumentMarker = true;
                            } else if (iter.isNextWordCursorWord()) {
                                if (processedCommand.getPositionalForNextValue() != null) {
                                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.ARGUMENT, word.word()));
                                } else {
                                    boolean emptyCursorWord = word.word() == null || word.word().isEmpty();
                                    processedCommand.setCompleteStatus(new CompleteStatus(processedCommand.hasOptions() && emptyCursorWord ? CompleteStatus.Status.COMPLETE_OPTION : CompleteStatus.Status.ARGUMENT_ERROR, null));
                                }
                            } else {
                                setCompletionArgStatus(word.word());
                                if (processedCommand.stopAtFirstPositional())
                                    argumentMarker = true;
                            }
                            iter.pollParsedWord();
                        }
                    }
                }
                if (argumentMarker && processedCommand.completeStatus() == null)
                    setCompletionArgStatus(null);
            } catch (OptionParserException e) {
                String msg = e.getMessage();
                if (msg != null && msg.contains("no value was given")) {
                    // Option was recognized but needs a value — offer value completion
                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.OPTION_MISSING_VALUE, ""));
                } else {
                    // Genuine parse error (unknown option in group, property syntax error, etc.)
                    processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.INVALID_INPUT, msg != null ? msg : ""));
                }
            }
        }
    }

    private void setCompletionArgStatus(String word) {
        ProcessedOption positional = processedCommand.getPositionalForNextValue();
        if (positional != null) {
            positional.addValue(word);
            processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.ARGUMENT, null));
        } else if (processedCommand.hasOptions() && (word == null || word.isEmpty())) {
            processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.COMPLETE_OPTION, null));
        } else if (processedCommand.hasArgument()) {
            // singular argument already filled and no @Arguments to overflow into
            processedCommand.setCompleteStatus(new CompleteStatus(CompleteStatus.Status.ARGUMENT_ERROR, null));
        }
    }

    private RequiredOptionException checkForMissingRequiredOptions(ProcessedCommand<? extends Command<CI>, CI> command) {
        for (ProcessedOption o : command.getOptions()) {
            if (doCheckForMissingRequiredOption(o))
                return new RequiredOptionException("Option: " + o.getDisplayName() + " is required for this command.");
        }
        for (ProcessedOption argOpt : command.getArgumentOptions()) {
            if (doCheckForMissingRequiredOption(argOpt))
                return generateRequiredExceptionFor(argOpt, false);
        }
        if (command.getArguments() != null) {
            if (doCheckForMissingRequiredOption(command.getArguments()))
                return generateRequiredExceptionFor(command.getArguments(), true);
        }
        return null;
    }

    private RequiredOptionException checkArityMin(ProcessedOption arg) {
        if (arg == null || arg.getArity() == null)
            return null;
        int count = arg.getValues().size();
        int min = arg.getArity().getMin();
        if (count < min) {
            String label = arg.getDisplayLabel();
            return new RequiredOptionException("Argument '" + label + "' requires at least " + min + " value" + (min > 1 ? "s" : "") + ", but got " + count + ".");
        }
        return null;
    }

    private RequiredOptionException generateRequiredExceptionFor(ProcessedOption argument, boolean plural) {
        final String description = argument.description();
        String msg;
        if (description != null && !description.isEmpty()) {
            msg = description;
        } else {
            msg = "Argument '" + argument.getFieldName() + "'";
        }
        msg += (plural ? " are " : " is ") + "required for this command.";
        return new RequiredOptionException(msg);
    }

    private boolean doCheckForMissingRequiredOption(ProcessedOption o) {
        if (o.isRequired() && o.getValue() == null) {
            boolean found = false;
            for (ProcessedOption po : processedCommand.getOptions()) {
                if (po.getValue() != null && po.doOverrideRequired()) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return true;
        }
        return false;
    }

    private MutuallyExclusiveOptionException checkForMutuallyExclusiveOptions(ProcessedCommand<? extends Command<CI>, CI> command) {
        for (ProcessedOption o : command.getOptions()) {
            if (o.getExclusiveWith().isEmpty() || o.getValue() == null)
                continue;
            for (String exclusiveName : o.getExclusiveWith()) {
                ProcessedOption other = command.findLongOptionNoActivatorCheck(exclusiveName);
                if (other != null && other.getValue() != null) {
                    return new MutuallyExclusiveOptionException("Options --" + o.name() + " and --" + other.name() + " are mutually exclusive.");
                }
            }
        }
        return null;
    }

    @Override
    public ProcessedOption lastParsedOption() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parse a command line with the defined command as base of the rules.
     * If any options are found, but not defined in the command object an
     * CommandLineParserException will be thrown.
     * Also, if a required option is not found or options specified with value,
     * but is not given any value an CommandLineParserException will be thrown.
     *
     * @param line input
     * @param mode parser mode
     */
    @Override
    public void parse(String line, Mode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isGroupCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getFormattedCommand(int offset, int descriptionStart) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Search parent parsers for an inherited option matching the given input.
     */
    private ProcessedOption searchParentInheritedOption(String word) {
        AeshCommandLineParser<CI> p = parent;
        while (p != null) {
            ProcessedOption option = p.getProcessedCommand().searchAllOptions(word);
            if (option != null && option.isInherited())
                return option;
            p = p.parent;
        }
        return null;
    }

    private String resolveDescriptionVariables(String rawDescription, ProcessedOption option) {
        String fullName = helpNames();
        String parentFullName = null;
        String parentName = null;
        if (isChild() && parent != null) {
            parentFullName = parent.helpNames();
            parentName = parent.getProcessedCommand().name();
        }
        if (option == null) {
            return getProcessedCommand().resolveCommandDescription(rawDescription, getProcessedCommand().name(), fullName, getRootProcessedCommand().name(), parentName, parentFullName);
        }
        return getProcessedCommand().resolveOptionDescription(option, getProcessedCommand().name(), fullName, getRootProcessedCommand().name(), parentName, parentFullName);
    }

    private ProcessedCommand<Command<CI>, CI> getRootProcessedCommand() {
        AeshCommandLineParser<CI> root = this;
        while (root.parent != null) root = root.parent;
        return root.getProcessedCommand();
    }

    /**
     * After populating parent and children, propagate inherited option values
     * from this (parent) command into parsed child commands that have matching fields.
     */
    private void propagateInheritedOptions() {
        for (ProcessedOption parentOpt : processedCommand.getOptions()) {
            if (!parentOpt.isInherited())
                continue;
            Object value = parentOpt.getFieldValue(getCommand());
            if (value == null)
                continue;
            for (CommandLineParser<CI> child : getChildParsers()) {
                if (child.parsedCommand() == null)
                    continue;
                Command<CI> childCmd = child.getCommand();
                ProcessedOption childOpt = child.getProcessedCommand().findLongOptionNoActivatorCheck(parentOpt.name());
                if (childOpt != null) {
                    if (childOpt.getValues() != null && !childOpt.getValues().isEmpty())
                        continue;
                    childOpt.setFieldValue(childCmd, value);
                } else {
                    try {
                        Field childField = findField(childCmd.getClass(), parentOpt.getFieldName());
                        if (childField == null)
                            continue;
                        if (!Modifier.isPublic(childField.getModifiers()))
                            childField.setAccessible(true);
                        childField.set(childCmd, value);
                    } catch (Exception e) {
                        // inherited value propagation is best-effort
                    }
                }
            }
        }
    }

    private Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.getName().equals(fieldName))
                    return f;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    @Override
    public void updateAnsiMode(boolean mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
