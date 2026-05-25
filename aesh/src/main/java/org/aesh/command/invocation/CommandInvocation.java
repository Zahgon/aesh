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
package org.aesh.command.invocation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.aesh.command.Command;
import org.aesh.command.CommandException;
import org.aesh.command.CommandNotFoundException;
import org.aesh.command.Executor;
import org.aesh.command.impl.context.CommandContext;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.shell.Shell;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.KeyAction;

/**
 * A CommandInvocation is the value object passed to a Command when it is executed.
 * It contain references to the current ControlOperator, registry, shell, ++
 *
 * @author Aesh team
 */
public interface CommandInvocation {

    /**
     * @return the shell
     */
    Shell getShell();

    /**
     * Specify the prompt
     */
    void setPrompt(Prompt prompt);

    /**
     * @return Get the current Prompt
     */
    Prompt getPrompt();

    /**
     * @return a formatted usage/help info from the specified command
     */
    String getHelpInfo(String commandName);

    /**
     * @return a formatted usage/help info from the running command
     */
    String getHelpInfo();

    /**
     * Stop the console and end the session
     */
    void stop();

    /**
     * Get the configuration.
     *
     * @return The configuration.
     */
    CommandInvocationConfiguration getConfiguration();

    /**
     * A blocking call that will return user input from the terminal
     *
     * @return user input
     * @throws InterruptedException
     */
    KeyAction input() throws InterruptedException;

    /**
     * A blocking call with a timeout that will return user input from the terminal
     *
     * @return user input or null if it times out
     * @throws InterruptedException on timeout
     */
    KeyAction input(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * A blocking call that will return user input from the terminal
     * after the user has pressed enter.
     *
     * @return user input line
     * @throws InterruptedException
     */
    String inputLine() throws InterruptedException;

    /**
     * A blocking call that will return user input from the terminal
     * after the user has pressed enter.
     *
     * @return user input line
     * @throws InterruptedException
     */
    String inputLine(Prompt prompt) throws InterruptedException;

    /**
     * This will push the input to the input stream where aesh will
     * parse it and execute it as a normal "user input".
     * The input will not be visible for the user.
     * Note that if this command still has the foreground this input
     * will just be sitting on the queue.
     *
     * @param input command input
     */
    void executeCommand(String input) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, CommandException, InterruptedException, IOException;

    Executor<? extends CommandInvocation> buildExecutor(String line) throws CommandNotFoundException, CommandLineParserException, OptionValidatorException, CommandValidatorException, IOException;

    /**
     * Print a message on console
     *
     * @param msg
     */
    default void print(String msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Print a new line with a message on console;
     *
     * @param msg
     */
    default void println(String msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Print a message on console
     *
     * @param msg
     * @param paging true to pause output for long content
     */
    void print(String msg, boolean paging);

    /**
     * Print a new line with a message on console;
     *
     * @param msg
     * @param paging true to pause output for long content
     */
    void println(String msg, boolean paging);

    // ========== Parent Command Context Methods ==========
    /**
     * Get the current command context for sub-command mode.
     * The context provides access to parent command values and state.
     *
     * @return the command context, or null if not available
     */
    default CommandContext getCommandContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a value from a parent command by field or option name.
     * Searches from immediate parent up to root.
     *
     * @param name The field or option name
     * @param type The expected type
     * @param <T> the value type
     * @return The value, or null if not found
     */
    default <T> T getParentValue(String name, Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a value from a parent command with a default value.
     *
     * @param name The field or option name
     * @param type The expected type
     * @param defaultValue The default value if not found
     * @param <T> the value type
     * @return The value, or defaultValue if not found
     */
    default <T> T getParentValue(String name, Class<T> type, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the immediate parent command instance.
     *
     * @return the parent command, or null if not in sub-command mode
     */
    default Command<?> getParentCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a specific parent command by type.
     *
     * @param type the command class to find
     * @param <T> the command type
     * @return the matching parent command, or null if not found
     */
    default <T extends Command<?>> T getParentCommand(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if currently executing in sub-command mode.
     *
     * @return true if in sub-command mode
     */
    default boolean isInSubCommandMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ========== Inherited Value Access Methods ==========
    /**
     * Get an inherited value from parent commands.
     * Only returns values from options marked with inherited=true.
     *
     * @param name The field or option name
     * @param type The expected type
     * @param <T> the value type
     * @return The inherited value, or null if not found
     */
    default <T> T getInheritedValue(String name, Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an inherited value from parent commands with a default.
     * Only returns values from options marked with inherited=true.
     *
     * @param name The field or option name
     * @param type The expected type
     * @param defaultValue The default value if not found
     * @param <T> the value type
     * @return The inherited value, or defaultValue if not found
     */
    default <T> T getInheritedValue(String name, Class<T> type, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Enter sub-command mode for the current group command.
     * This pushes the current command onto the context stack and changes the prompt.
     * Subsequent commands will have access to this command's values via
     * {@link #getParentValue} and {@link #getParentCommand}.
     *
     * Type 'exit' to leave sub-command mode.
     *
     * @param command The group command instance to push onto the context
     * @return true if sub-command mode was entered successfully
     */
    default boolean enterSubCommandMode(Command<?> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Exit the current sub-command mode level.
     * This pops the current context and restores the previous prompt.
     *
     * @return true if a context level was exited, false if not in sub-command mode
     */
    default boolean exitSubCommandMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ========== Hyperlink Methods ==========
    /**
     * Print a hyperlink to the terminal. If the terminal supports OSC 8 hyperlinks,
     * the text will be rendered as a clickable link. Otherwise, plain text is printed.
     *
     * @param url the URL target of the hyperlink
     * @param text the visible text for the hyperlink
     */
    default void printHyperlink(String url, String text) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the terminal supports OSC 8 hyperlinks.
     *
     * @return true if hyperlinks are supported
     */
    default boolean supportsHyperlinks() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the standard input stream for this command.
     * This is available when the command receives piped input ({@code cmd1 | cmd2})
     * or redirected input ({@code cmd < file}).
     *
     * @return the input stream, or null if no piped/redirected input
     */
    default java.io.InputStream getStdin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether this command has piped or redirected standard input available.
     * This method does not allocate any streams.
     *
     * @return true if piped or redirected input is available
     */
    default boolean hasStdin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
