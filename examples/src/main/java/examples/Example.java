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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.aesh.command.Command;
import org.aesh.command.CommandDefinition;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.GroupCommandDefinition;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.activator.OptionActivator;
import org.aesh.command.builder.CommandBuilder;
import org.aesh.command.completer.CompleterInvocation;
import org.aesh.command.completer.OptionCompleter;
import org.aesh.command.converter.ConverterInvocation;
import org.aesh.command.impl.internal.ParsedCommand;
import org.aesh.command.impl.internal.ParsedOption;
import org.aesh.command.impl.internal.ProcessedOptionBuilder;
import org.aesh.command.impl.registry.AeshCommandRegistryBuilder;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.option.Arguments;
import org.aesh.command.option.Option;
import org.aesh.command.option.OptionList;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.registry.CommandRegistryException;
import org.aesh.command.renderer.OptionRenderer;
import org.aesh.command.settings.ManProvider;
import org.aesh.command.settings.SettingsBuilder;
import org.aesh.command.shell.Shell;
import org.aesh.command.validator.OptionValidator;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.command.validator.ValidatorInvocation;
import org.aesh.command.validator.ValidatorInvocationProvider;
import org.aesh.console.AeshContext;
import org.aesh.console.ReadlineConsole;
import org.aesh.io.FileResource;
import org.aesh.io.Resource;
import org.aesh.readline.prompt.Prompt;
import org.aesh.selector.Selector;
import org.aesh.selector.SelectorType;
import org.aesh.terminal.Key;
import org.aesh.terminal.KeyAction;
import org.aesh.terminal.formatting.CharacterType;
import org.aesh.terminal.formatting.Color;
import org.aesh.terminal.formatting.TerminalColor;
import org.aesh.terminal.formatting.TerminalString;
import org.aesh.terminal.formatting.TerminalTextStyle;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Config;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class Example {

    public static void main(String[] args) throws CommandLineParserException, IOException, CommandRegistryException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @CommandDefinition(name = "exit", description = "exit the program", aliases = { "quit" })
    public static class ExitCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static class HideActivator implements CommandActivator {

        @Override
        public boolean isActivated(ParsedCommand command) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "hidden", description = "hidden command", activator = HideActivator.class)
    public static class HiddenCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "run", description = "")
    public static class RunCommand implements Command {

        @Arguments(required = true)
        private List<Resource> arguments;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private List<String> readScriptFile(Resource resource) throws IOException {
            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.read()));
            String line = br.readLine();
            while (line != null) {
                if (line.trim().length() > 0 && !line.trim().startsWith("#"))
                    lines.add(line);
                line = br.readLine();
            }
            return lines;
        }
    }

    //this command use a builder defined above to specify the meta data needed
    public static class FooCommand implements Command<CommandInvocation> {

        private String bar;

        private String foo;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "test", description = "testing")
    public static class TestConsoleCommand implements Command {

        @Option(hasValue = false, required = true)
        private boolean verbose;

        @Option(hasValue = false)
        private boolean barbar;

        @Option(overrideRequired = true, hasValue = false)
        private boolean help;

        @Option(hasValue = false)
        private boolean helpPlease;

        private Shell shell;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void display() {
            shell.write(ANSI.ALTERNATE_BUFFER);
        }

        private void stop() {
            shell.write(ANSI.MAIN_BUFFER);
        }

        public void processOperation(CommandInvocation invocation) throws IOException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private String promptForUsername(CommandInvocation invocation) throws InterruptedException {
            invocation.print("username: ");
            return invocation.inputLine();
        }

        private String promptForInput(String prompt, Character mask, CommandInvocation invocation) throws IOException, InterruptedException {
            return invocation.inputLine(new Prompt(prompt, mask));
        }
    }

    @CommandDefinition(name = "ls", description = "[OPTION]... [FILE]...", version = "1.0")
    public static class LsCommand implements Command {

        @Option(shortName = 'f', hasValue = false, description = "set foo to true/false")
        private Boolean foo;

        @Option(hasValue = false, description = "set the bar", renderer = BlueBoldRenderer.class)
        private boolean bar;

        @Option(shortName = 'l', completer = LessCompleter.class, defaultValue = { "MORE" }, argument = "SIZE")
        private String less;

        @OptionList(defaultValue = "/tmp", description = "file location", valueSeparator = ':', validator = DirectoryValidator.class, activator = BarActivator.class)
        List<File> files;

        @Option(hasValue = false, description = "display this help and exit")
        private boolean help;

        @Arguments(description = "files or directories thats listed")
        private List<Resource> arguments;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "ask", description = "")
    public static class AskCommand implements Command {

        @Option(askIfNotSet = true)
        private String path;

        @Arguments(askIfNotSet = true)
        private List<String> args;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "clear", description = "")
    public static class ClearCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "long-output", description = "")
    public static class LongOutputCommand implements Command {

        @Option(hasValue = false)
        private boolean prompt;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "prompt", description = "")
    public static class PromptCommand implements Command {

        @Option(hasValue = false)
        private boolean bar;

        private Shell shell;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void processOperation(Key operation) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "select", description = "The select command", generateHelp = true)
    public static class SelectCommand implements Command {

        @Option(selector = SelectorType.SELECT, description = "Choose your color", defaultValue = { "red", "green", "blue" })
        private String color;

        @Option(selector = SelectorType.SELECT, description = "Set to true or false")
        private boolean bool;

        private Shell shell;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "readline", description = "")
    public static class ReadlineCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class LessCompleter implements OptionCompleter {

        @Override
        public void complete(CompleterInvocation completerData) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class DirectoryValidator implements OptionValidator<DirectoryValidatorInvocation> {

        @Override
        public void validate(DirectoryValidatorInvocation validatorInvocation) throws OptionValidatorException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class DirectoryValidatorInvocation implements ValidatorInvocation<File, Command> {

        private final File file;

        private final Command command;

        private final AeshContext aeshContext;

        public DirectoryValidatorInvocation(File file, Command command, AeshContext aeshContext) {
            this.file = file;
            this.command = command;
            this.aeshContext = aeshContext;
        }

        @Override
        public File getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Command getCommand() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public AeshContext getAeshContext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class ExampleValidatorInvocationProvider implements ValidatorInvocationProvider {

        @Override
        public ValidatorInvocation enhanceValidatorInvocation(ValidatorInvocation validatorInvocation) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class BarActivator implements OptionActivator {

        @Override
        public boolean isActivated(ParsedCommand parsedCommand) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class BlueBoldRenderer implements OptionRenderer {

        private static final TerminalTextStyle style = new TerminalTextStyle(CharacterType.UNDERLINE);

        private static final TerminalColor color = new TerminalColor(42, Color.BLUE);

        @Override
        public TerminalColor getColor() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public TerminalTextStyle getTextType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static class ManProviderExample implements ManProvider {

        @Override
        public InputStream getManualDocument(String commandName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @GroupCommandDefinition(name = "group", description = "This is a group command", groupCommands = { Base.class, Rebase.class })
    public static class GroupCommand implements Command {

        @Option(hasValue = false, description = "display this help option")
        private boolean help;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "base", description = "description of base command")
    public static class Base implements Command {

        @Option(description = "set foo")
        private String foo;

        @Option(hasValue = false, description = "display this help option")
        private boolean help;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "rebase", description = "description of rebase command")
    public static class Rebase implements Command {

        @Option
        private boolean bar;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
