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
package org.aesh.command.impl.context;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aesh.command.Command;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.settings.SubCommandModeSettings;

/**
 * Tracks the current command context state for sub-command mode.
 * Maintains a stack of context frames, each representing a group command
 * that has been entered. Provides access to parent command values.
 *
 * @author Aesh team
 */
public class CommandContext {

    private final Deque<ContextFrame> contextStack;

    private final String originalPrompt;

    private SubCommandModeSettings settings;

    public CommandContext(String originalPrompt) {
        this(originalPrompt, SubCommandModeSettings.defaults());
    }

    public CommandContext(String originalPrompt, SubCommandModeSettings settings) {
        this.contextStack = new ArrayDeque<>();
        this.originalPrompt = originalPrompt;
        this.settings = settings != null ? settings : SubCommandModeSettings.defaults();
    }

    /**
     * Get the sub-command mode settings.
     *
     * @return the settings
     */
    public SubCommandModeSettings getSettings() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the sub-command mode settings.
     *
     * @param settings the settings
     */
    public void setSettings(SubCommandModeSettings settings) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Push a new context frame when entering a group command.
     *
     * @param parser The group command's parser
     * @param command The populated command instance with parsed values
     */
    public void push(CommandLineParser<?> parser, Command<?> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Pop the current context frame when exiting.
     *
     * @return the popped context frame
     */
    public ContextFrame pop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the current context frame.
     *
     * @return the current frame, or null if not in sub-command mode
     */
    public ContextFrame current() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if we're in sub-command mode.
     *
     * @return true if in sub-command mode
     */
    public boolean isInSubCommandMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the depth of the context stack.
     *
     * @return the number of nested contexts
     */
    public int depth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the context path as a string (e.g., "module:project").
     *
     * @return the context path
     */
    public String getContextPath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the context path with spaces for command prefixing (e.g., "module project").
     *
     * @return the context path with spaces
     */
    public String getContextPathWithSpaces() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Build a prompt showing the current context.
     * Examples: "module> ", "module[my-module]> ", "module:project> "
     *
     * @param showArgumentInPrompt whether to show primary argument in prompt
     * @return the formatted prompt string
     */
    public String buildPrompt(boolean showArgumentInPrompt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the original prompt before entering sub-command mode.
     *
     * @return the original prompt string
     */
    public String getOriginalPrompt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the given command is an exit command for sub-command mode.
     *
     * @param command the command to check
     * @return true if the command should exit sub-command mode
     */
    public boolean isExitCommand(String command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Format the enter message for sub-command mode.
     *
     * @param commandName the name of the command being entered
     * @return the formatted enter message, or null if no message configured
     */
    public String formatEnterMessage(String commandName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Format the exit hint for sub-command mode.
     *
     * @return the formatted exit hint, or null if no hint configured
     */
    public String formatExitHint() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Format the exit message for sub-command mode.
     *
     * @param commandName the name of the command being exited
     * @return the formatted exit message, or null if no message configured
     */
    public String formatExitMessage(String commandName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ========== Parent Value Access Methods ==========
    /**
     * Get a value from any parent command in the context stack.
     * Searches from immediate parent up to root.
     *
     * @param fieldName The field name to look for
     * @param type The expected type
     * @param <T> the value type
     * @return The value, or null if not found
     */
    public <T> T getParentValue(String fieldName, Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a value from any parent command with a default.
     *
     * @param fieldName The field name to look for
     * @param type The expected type
     * @param defaultValue The default value if not found
     * @param <T> the value type
     * @return The value, or defaultValue if not found
     */
    public <T> T getParentValue(String fieldName, Class<T> type, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the immediate parent command instance.
     *
     * @return the parent command, or null if not in sub-command mode
     */
    public Command<?> getParentCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get all parent commands from immediate to root.
     *
     * @return list of parent commands
     */
    public List<Command<?>> getParentCommands() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a specific parent command by type.
     *
     * @param type the command class to find
     * @param <T> the command type
     * @return the matching parent command, or null if not found
     */
    @SuppressWarnings("unchecked")
    public <T extends Command<?>> T getParentCommand(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ========== Inherited Value Access Methods ==========
    /**
     * Get an inherited value from parent commands.
     * Only returns values from options marked with inherited=true.
     *
     * @param fieldName The field name to look for
     * @param type The expected type
     * @param <T> the value type
     * @return The inherited value, or null if not found
     */
    public <T> T getInheritedValue(String fieldName, Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get an inherited value from parent commands with a default.
     * Only returns values from options marked with inherited=true.
     *
     * @param fieldName The field name to look for
     * @param type The expected type
     * @param defaultValue The default value if not found
     * @param <T> the value type
     * @return The inherited value, or defaultValue if not found
     */
    public <T> T getInheritedValue(String fieldName, Class<T> type, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get all inherited options from the context stack.
     * Returns a map of field names to their values for all inherited options.
     *
     * @return map of inherited field names to values
     */
    public Map<String, Object> getAllInheritedValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get all inherited options with their ProcessedOption metadata.
     * Returns a map of field names to ProcessedOptions for injection.
     *
     * @return map of field names to ProcessedOptions
     */
    public Map<String, ProcessedOption> getAllInheritedOptions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Format context values for display.
     *
     * @return formatted string showing all context values
     */
    public String formatContextValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // ========== Context Frame Inner Class ==========
    /**
     * Represents a single frame in the context stack.
     * Contains the parser, command instance, and provides value access.
     */
    public static class ContextFrame {

        private final CommandLineParser<?> parser;

        private final Command<?> command;

        private final Map<String, Object> cachedValues;

        private final Map<String, Object> inheritedValues;

        private final Map<String, ProcessedOption> inheritedOptions;

        public ContextFrame(CommandLineParser<?> parser, Command<?> command) {
            this.parser = parser;
            this.command = command;
            this.cachedValues = new HashMap<>();
            this.inheritedValues = new HashMap<>();
            this.inheritedOptions = new HashMap<>();
            cacheFieldValues();
        }

        private void cacheFieldValues() {
            // Cache all option and argument values for quick access
            ProcessedCommand<?, ?> pc = parser.getProcessedCommand();
            for (ProcessedOption opt : pc.getOptions()) {
                Object value = getFieldValueByReflection(opt.getFieldName());
                if (value != null) {
                    cachedValues.put(opt.getFieldName(), value);
                    // Also cache by option name for convenience
                    if (opt.name() != null && !opt.name().isEmpty()) {
                        cachedValues.put(opt.name(), value);
                    }
                    // Track inherited options separately
                    if (opt.isInherited()) {
                        inheritedValues.put(opt.getFieldName(), value);
                        inheritedOptions.put(opt.getFieldName(), opt);
                        if (opt.name() != null && !opt.name().isEmpty()) {
                            inheritedValues.put(opt.name(), value);
                        }
                    }
                }
            }
            // Cache singular arguments
            for (ProcessedOption arg : pc.getArgumentOptions()) {
                Object value = getFieldValueByReflection(arg.getFieldName());
                if (value != null) {
                    cachedValues.put(arg.getFieldName(), value);
                    cachedValues.put("_argument", value);
                    // Track inherited argument
                    if (arg.isInherited()) {
                        inheritedValues.put(arg.getFieldName(), value);
                        inheritedOptions.put(arg.getFieldName(), arg);
                    }
                }
            }
            // Cache arguments (list)
            if (pc.getArguments() != null) {
                Object value = getFieldValueByReflection(pc.getArguments().getFieldName());
                if (value != null) {
                    cachedValues.put(pc.getArguments().getFieldName(), value);
                    cachedValues.put("_arguments", value);
                }
            }
        }

        private Object getFieldValueByReflection(String fieldName) {
            try {
                Field field = findField(command.getClass(), fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    return field.get(command);
                }
            } catch (IllegalAccessException e) {
                // Ignore, return null
            }
            return null;
        }

        private Field findField(Class<?> clazz, String fieldName) {
            while (clazz != null) {
                try {
                    return clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }
            return null;
        }

        /**
         * Get the command name.
         *
         * @return the command name
         */
        public String getCommandName() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the command instance with cached values restored.
         * This is important because parsing subsequent commands may reset the command's fields.
         *
         * @return the command with restored values
         */
        public Command<?> getCommand() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Restore cached values back to the command instance.
         * This is needed because parsing may reset the command's fields.
         */
        private void restoreCachedValues() {
            for (Map.Entry<String, Object> entry : cachedValues.entrySet()) {
                try {
                    Field field = findField(command.getClass(), entry.getKey());
                    if (field != null) {
                        field.setAccessible(true);
                        field.set(command, entry.getValue());
                    }
                } catch (IllegalAccessException e) {
                    // Ignore, continue with other fields
                }
            }
        }

        /**
         * Get the parser.
         *
         * @return the command line parser
         */
        public CommandLineParser<?> getParser() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get a field value by name.
         *
         * @param fieldName the field name
         * @return the value, or null if not found
         */
        public Object getFieldValue(String fieldName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the primary value for display in prompt.
         * Looks for arguments first, then common option names like "name", "projectName".
         *
         * @return the primary value as string, or null if none
         */
        public String getPrimaryArgumentValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get all cached values.
         *
         * @return unmodifiable map of all values
         */
        public Map<String, Object> getAllValues() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get an inherited value by field or option name.
         *
         * @param fieldName the field or option name
         * @return the inherited value, or null if not found or not inherited
         */
        public Object getInheritedValue(String fieldName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get all inherited values from this frame.
         *
         * @return unmodifiable map of inherited values
         */
        public Map<String, Object> getInheritedValues() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get all inherited options from this frame.
         *
         * @return unmodifiable map of inherited ProcessedOptions
         */
        public Map<String, ProcessedOption> getInheritedOptions() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Format entry message showing all values.
         *
         * @return formatted string for display
         */
        public String formatEntryMessage() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
