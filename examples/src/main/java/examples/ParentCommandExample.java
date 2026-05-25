/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
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
package examples;

import java.io.IOException;
import org.aesh.command.Command;
import org.aesh.command.CommandDefinition;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.GroupCommandDefinition;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.activator.OptionActivator;
import org.aesh.command.completer.CompleterInvocation;
import org.aesh.command.converter.ConverterInvocation;
import org.aesh.command.impl.registry.AeshCommandRegistryBuilder;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.option.Option;
import org.aesh.command.option.ParentCommand;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.registry.CommandRegistryException;
import org.aesh.command.settings.SettingsBuilder;
import org.aesh.command.settings.SubCommandModeSettings;
import org.aesh.command.validator.ValidatorInvocation;
import org.aesh.console.ReadlineConsole;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.formatting.Color;
import org.aesh.terminal.formatting.TerminalColor;
import org.aesh.terminal.formatting.TerminalString;

/**
 * Example demonstrating parent context access in subcommands.
 *
 * This example shows how subcommands can access values from their parent group command
 * using three different approaches:
 * 1. Using @ParentCommand annotation for direct field injection
 * 2. Using CommandInvocation.getParentValue() for programmatic access
 * 3. Using inherited=true on parent options for automatic field population
 *
 * === SUB-COMMAND MODE (recommended) ===
 * Enter sub-command mode first, then run subcommands:
 * project --name=myapp --verbose (enters sub-command mode, prompt changes to "project[myapp]> ")
 * build (inherits --verbose via inherited=true)
 * test --coverage (inherits parent options)
 * status (uses inherited --verbose option)
 * context (displays current context values)
 * exit (returns to main prompt, or use "..")
 *
 * === DIRECT INVOCATION (alternative) ===
 * When calling subcommands directly, use the --name option on the subcommand:
 * project build --name=myapp
 * project test --name=myapp --coverage
 * project deploy --name=myapp --env=production
 *
 * @author Ståle W. Pedersen
 */
public class ParentCommandExample {

    public static void main(String[] args) throws CommandLineParserException, IOException, CommandRegistryException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @CommandDefinition(name = "exit", description = "Exit the application", aliases = { "quit" })
    public static class ExitCommand implements Command<CommandInvocation> {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Parent group command that defines project-level options.
     * Subcommands can access these options via @ParentCommand or CommandInvocation.
     *
     * When executed without a subcommand, enters sub-command mode where
     * subsequent commands have access to the project's options.
     */
    @GroupCommandDefinition(name = "project", description = "Project management commands", groupCommands = { BuildCommand.class, TestCommand.class, DeployCommand.class, StatusCommand.class })
    public static class ProjectCommand implements Command<CommandInvocation> {

        @Option(name = "name", shortName = 'n', required = true, description = "Project name")
        private String projectName;

        // inherited=true makes this option automatically available to subcommands
        // Subcommands with a "verbose" field will get the value auto-populated
        @Option(name = "verbose", shortName = 'v', hasValue = false, description = "Enable verbose output", inherited = true)
        private boolean verbose;

        @Option(name = "config", shortName = 'c', description = "Configuration file path")
        private String configFile;

        // Getters for subcommands to access
        public String getProjectName() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isVerbose() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getConfigFile() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public CommandResult execute(CommandInvocation invocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Build subcommand - demonstrates @ParentCommand annotation.
     * The parent ProjectCommand is automatically injected when in sub-command mode.
     *
     * Usage:
     * 1. Enter sub-command mode: project --name=myapp --verbose
     * 2. Then run: build --target=jar
     *
     * Or use direct invocation with local options:
     * project build --name=myapp --target=jar
     */
    @CommandDefinition(name = "build", description = "Build the project")
    public static class BuildCommand implements Command<CommandInvocation> {

        // Parent command is injected when in sub-command mode
        @ParentCommand
        private ProjectCommand parent;

        // Local option that can also be used for direct invocation
        @Option(name = "name", shortName = 'n', description = "Project name (use when not in sub-command mode)")
        private String name;

        @Option(name = "target", shortName = 't', defaultValue = { "jar" }, description = "Build target (jar, war, native)")
        private String target;

        @Option(name = "skip-tests", hasValue = false, description = "Skip running tests during build")
        private boolean skipTests;

        @Override
        public CommandResult execute(CommandInvocation invocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Test subcommand - demonstrates CommandInvocation.getParentValue() approach.
     * This is useful when you don't want a direct dependency on the parent class.
     *
     * Usage:
     * 1. Enter sub-command mode: project --name=myapp --verbose
     * 2. Then run: test --coverage
     *
     * Or use direct invocation with local options:
     * project test --name=myapp --coverage
     */
    @CommandDefinition(name = "test", description = "Run project tests")
    public static class TestCommand implements Command<CommandInvocation> {

        // Local option for direct invocation
        @Option(name = "name", shortName = 'n', description = "Project name (use when not in sub-command mode)")
        private String name;

        @Option(name = "coverage", hasValue = false, description = "Generate coverage report")
        private boolean coverage;

        @Option(name = "filter", description = "Filter tests by pattern")
        private String filter;

        @Override
        public CommandResult execute(CommandInvocation invocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Deploy subcommand - demonstrates both approaches combined.
     *
     * Usage:
     * 1. Enter sub-command mode: project --name=myapp --verbose
     * 2. Then run: deploy --env=production
     *
     * Or use direct invocation with local options:
     * project deploy --name=myapp --env=production
     */
    @CommandDefinition(name = "deploy", description = "Deploy the project")
    public static class DeployCommand implements Command<CommandInvocation> {

        // Direct injection for type-safe access (works in sub-command mode)
        @ParentCommand
        private ProjectCommand parent;

        // Local option for direct invocation
        @Option(name = "name", shortName = 'n', description = "Project name (use when not in sub-command mode)")
        private String name;

        @Option(name = "env", shortName = 'e', required = true, description = "Target environment (dev, staging, production)")
        private String environment;

        @Option(name = "dry-run", hasValue = false, description = "Simulate deployment without making changes")
        private boolean dryRun;

        @Override
        public CommandResult execute(CommandInvocation invocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Status subcommand - demonstrates inherited option auto-population.
     *
     * The "verbose" field will be automatically populated from the parent's
     * inherited verbose option when in sub-command mode.
     *
     * This approach is cleaner than @ParentCommand or getParentValue() when you
     * just need specific option values and want them auto-populated.
     *
     * Usage:
     * 1. Enter sub-command mode: project --name=myapp --verbose
     * 2. Then run: status
     * (verbose field is auto-populated from parent)
     */
    @CommandDefinition(name = "status", description = "Show project status")
    public static class StatusCommand implements Command<CommandInvocation> {

        // This field has the same name as the parent's inherited option.
        // It will be automatically populated when in sub-command mode.
        @Option(name = "verbose", shortName = 'v', hasValue = false, description = "Show detailed status")
        private boolean verbose;

        @Override
        public CommandResult execute(CommandInvocation invocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
