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
package org.aesh;

import java.io.IOException;
import java.util.Arrays;
import org.aesh.command.AeshCommandRuntimeBuilder;
import org.aesh.command.Command;
import org.aesh.command.CommandException;
import org.aesh.command.CommandNotFoundException;
import org.aesh.command.CommandResult;
import org.aesh.command.CommandRuntime;
import org.aesh.command.DefaultValueProvider;
import org.aesh.command.container.CommandContainer;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.impl.registry.AeshCommandRegistryBuilder;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.registry.CommandRegistryException;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.complete.AeshCompleteOperation;
import org.aesh.util.completer.ShellCompletionGenerator;
import org.aesh.util.completer.ShellCompletionGenerator.ShellType;

/**
 * Non-interactive single-command runner for CLI tools.
 * For interactive shells, use {@link AeshConsoleRunner}.
 */
public class AeshRuntimeRunner {

    private final AeshCommandRegistryBuilder registryBuilder = AeshCommandRegistryBuilder.builder();

    private String[] args;

    private ShellType completionShellType;

    private ShellType dynamicCompletionShellType;

    private String completionProgramName;

    private boolean dynamicComplete;

    private AeshRuntimeRunner() {
    }

    public static AeshRuntimeRunner builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set a custom CommandContainerBuilder for command creation.
     * This allows frameworks (e.g., CDI, Spring) to inject dependencies
     * into subcommands and option service providers.
     */
    public AeshRuntimeRunner containerBuilder(org.aesh.command.container.CommandContainerBuilder<?> containerBuilder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner command(Class<? extends Command> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner command(Command commandInstance) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner args(String... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner generateCompletion(ShellType shellType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner completionProgramName(String programName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner generateDynamicCompletion(ShellType shellType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AeshRuntimeRunner dynamicComplete(boolean dynamicComplete) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set a registry-level DefaultValueProvider that applies to all commands
     * that don't declare their own per-command provider via the annotation.
     */
    public AeshRuntimeRunner defaultValueProvider(DefaultValueProvider provider) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public CommandResult execute() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private CommandResult generateCompletionScript(CommandRegistry commandRegistry) {
        try {
            String commandName = (String) commandRegistry.getAllCommandNames().iterator().next();
            CommandContainer<CommandInvocation> container = (CommandContainer<CommandInvocation>) commandRegistry.getCommand(commandName, "");
            String programName = completionProgramName != null ? completionProgramName : commandName;
            String script = ShellCompletionGenerator.forShell(completionShellType).generate(container.getParser(), programName);
            System.out.print(script);
            return CommandResult.SUCCESS;
        } catch (CommandNotFoundException e) {
            System.err.println("Command not found: " + e.getMessage());
            return CommandResult.FAILURE;
        }
    }

    @SuppressWarnings("unchecked")
    private CommandResult generateDynamicCompletionScript(CommandRegistry commandRegistry) {
        try {
            String commandName = (String) commandRegistry.getAllCommandNames().iterator().next();
            CommandContainer<CommandInvocation> container = (CommandContainer<CommandInvocation>) commandRegistry.getCommand(commandName, "");
            String programName = completionProgramName != null ? completionProgramName : commandName;
            String script = ShellCompletionGenerator.forShell(dynamicCompletionShellType).generateDynamic(container.getParser(), programName);
            System.out.print(script);
            return CommandResult.SUCCESS;
        } catch (CommandNotFoundException e) {
            System.err.println("Command not found: " + e.getMessage());
            return CommandResult.FAILURE;
        }
    }

    @SuppressWarnings("unchecked")
    private CommandResult performDynamicCompletion(CommandRegistry commandRegistry) {
        try {
            CommandRuntime<CommandInvocation> rt = AeshCommandRuntimeBuilder.builder().commandRegistry(commandRegistry).build();
            String commandName = (String) commandRegistry.getAllCommandNames().iterator().next();
            String partialLine = args != null ? String.join(" ", args) : "";
            String buffer = partialLine.isEmpty() ? commandName + " " : commandName + " " + partialLine;
            AeshCompleteOperation completeOperation = new AeshCompleteOperation(buffer, buffer.length());
            rt.complete(completeOperation);
            // Build a map of subcommand/option names to descriptions for richer output
            java.util.Map<String, String> descriptions = buildCompletionDescriptions(commandRegistry, commandName);
            // For dynamic shell completion, filter out option candidates when the
            // cursor is at a positional argument position (no - prefix typed).
            // This lets the shell provide its default file completion instead.
            boolean cursorAtPositional = partialLine.isEmpty() || (!partialLine.endsWith("-") && (partialLine.endsWith(" ") || !partialLine.contains("-")));
            java.util.List<org.aesh.terminal.formatting.TerminalString> candidates = completeOperation.getCompletionCandidates();
            if (cursorAtPositional) {
                candidates.removeIf(c -> c.getCharacters().trim().startsWith("-"));
            }
            for (org.aesh.terminal.formatting.TerminalString candidate : candidates) {
                String value = candidate.getCharacters();
                String desc = descriptions.get(value.trim());
                if (desc != null && !desc.isEmpty()) {
                    System.out.println(value + "\t" + desc);
                } else {
                    System.out.println(value);
                }
            }
            return CommandResult.SUCCESS;
        } catch (Exception e) {
            System.err.println("Completion error: " + e.getMessage());
            return CommandResult.FAILURE;
        }
    }

    @SuppressWarnings("unchecked")
    private java.util.Map<String, String> buildCompletionDescriptions(CommandRegistry commandRegistry, String commandName) {
        java.util.Map<String, String> descriptions = new java.util.HashMap<>();
        try {
            CommandContainer<CommandInvocation> container = commandRegistry.getCommand(commandName, "");
            CommandLineParser<CommandInvocation> parser = container.getParser();
            // Find the deepest group command matching the partial line to scope descriptions
            CommandLineParser<CommandInvocation> scopedParser = findScopedParser(parser, args);
            // Add subcommand descriptions scoped to the current group
            if (scopedParser.isGroupCommand()) {
                for (CommandLineParser<CommandInvocation> child : scopedParser.getAllChildParsers()) {
                    String name = child.getProcessedCommand().name();
                    String desc = child.getProcessedCommand().description();
                    if (desc != null && !desc.isEmpty()) {
                        descriptions.put(name, desc);
                    }
                }
            }
            // Add option descriptions from the scoped parser
            addOptionDescriptions(descriptions, scopedParser);
        } catch (Exception ignored) {
        }
        return descriptions;
    }

    /**
     * Walk the parser tree to find the deepest group command matching the args.
     * For example, with args ["alias", ""] and a parser tree jbang > alias > {add, list, remove},
     * this returns the "alias" parser so descriptions come from alias's children only.
     */
    @SuppressWarnings("unchecked")
    private static <CI extends CommandInvocation> CommandLineParser<CI> findScopedParser(CommandLineParser<CI> root, String[] args) {
        if (args == null || args.length == 0)
            return root;
        CommandLineParser<CI> current = root;
        for (String arg : args) {
            if (arg == null || arg.isEmpty() || arg.startsWith("-"))
                break;
            if (!current.isGroupCommand())
                break;
            boolean found = false;
            for (CommandLineParser<CI> child : current.getAllChildParsers()) {
                if (child.getProcessedCommand().name().equals(arg)) {
                    current = child;
                    found = true;
                    break;
                }
            }
            if (!found)
                break;
        }
        return current;
    }

    private static void addOptionDescriptions(java.util.Map<String, String> descriptions, CommandLineParser<?> parser) {
        for (org.aesh.command.impl.internal.ProcessedOption opt : parser.getProcessedCommand().getOptions()) {
            String optName = "--" + opt.name();
            if (opt.description() != null && !opt.description().isEmpty()) {
                descriptions.put(optName, opt.description());
            }
            // Add description for negated form of negatable options
            if (opt.isNegatable() && opt.getNegatedName() != null) {
                String negatedName = "--" + opt.getNegatedName();
                if (opt.description() != null && !opt.description().isEmpty()) {
                    descriptions.put(negatedName, opt.description());
                }
            }
        }
    }

    public static boolean handleDynamicCompletion(String[] args, Class<? extends Command> commandClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Handles the --aesh-completion-install flag. Detects the user's shell,
     * generates a dynamic completion script, and installs it to the appropriate
     * location after user confirmation.
     *
     * @param args the command-line arguments
     * @param commandClass the command class to generate completions for
     * @return true if the flag was handled, false if not a completion install request
     */
    public static boolean handleCompletionInstall(String[] args, Class<? extends Command> commandClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Handles the --aesh-completion-install flag with a custom program name.
     *
     * @param args the command-line arguments
     * @param commandClass the command class to generate completions for
     * @param programName the program name to use in the completion script (null = use command name)
     * @return true if the flag was handled, false if not a completion install request
     */
    @SuppressWarnings("unchecked")
    public static boolean handleCompletionInstall(String[] args, Class<? extends Command> commandClass, String programName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ShellType detectShell() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static java.io.File getCompletionInstallPath(ShellType shellType, String programName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void showHelp(CommandRuntime runtime, String commandName, String[] args, Exception e) {
        System.err.println(e.getMessage());
        String helpLine = resolveHelpCommand(runtime, commandName, args);
        System.err.println(runtime.commandInfo(helpLine));
    }

    @SuppressWarnings("unchecked")
    private static String resolveHelpCommand(CommandRuntime runtime, String commandName, String[] args) {
        if (args == null || args.length == 0)
            return commandName;
        try {
            CommandContainer<CommandInvocation> container = (CommandContainer<CommandInvocation>) runtime.getCommandRegistry().getCommand(commandName, "");
            if (container.getParser().isGroupCommand()) {
                StringBuilder line = new StringBuilder(commandName);
                CommandLineParser<?> current = container.getParser();
                for (String arg : args) {
                    if (arg.startsWith("-"))
                        continue;
                    CommandLineParser<?> child = current.getChildParser(arg);
                    if (child != null) {
                        line.append(' ').append(arg);
                        if (child.isGroupCommand())
                            current = child;
                        else
                            return line.toString();
                    }
                }
                if (line.length() > commandName.length())
                    return line.toString();
            }
        } catch (CommandNotFoundException ex) {
            // fall through
        }
        return commandName;
    }
}
