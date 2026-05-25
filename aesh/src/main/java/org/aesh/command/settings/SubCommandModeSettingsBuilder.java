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

/**
 * Builder for SubCommandModeSettings.
 *
 * @author Aesh team
 */
public class SubCommandModeSettingsBuilder {

    private boolean enabled = true;

    private String exitCommand = "exit";

    private String alternativeExitCommand = "..";

    private String contextSeparator = ":";

    private boolean showContextOnEntry = true;

    private boolean showArgumentInPrompt = true;

    private String contextCommand = "context";

    private String enterMessage = "Entering {name} mode.";

    private String exitMessage = null;

    private String exitHint = "Type '{exit}' to return.";

    private boolean exitOnCtrlC = true;

    SubCommandModeSettingsBuilder() {
    }

    /**
     * Enable or disable sub-command mode globally.
     *
     * @param enabled true to enable (default: true)
     * @return this builder
     */
    public SubCommandModeSettingsBuilder enabled(boolean enabled) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the primary exit command.
     *
     * @param exitCommand the exit command (default: "exit")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder exitCommand(String exitCommand) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the alternative exit command.
     * Set to null to disable.
     *
     * @param alternativeExitCommand the alternative exit command (default: "..")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder alternativeExitCommand(String alternativeExitCommand) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the context path separator.
     *
     * @param contextSeparator the separator (default: ":")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder contextSeparator(String contextSeparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set whether to show context values when entering sub-command mode.
     *
     * @param showContextOnEntry true to show (default: true)
     * @return this builder
     */
    public SubCommandModeSettingsBuilder showContextOnEntry(boolean showContextOnEntry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set whether to show argument value in the prompt.
     *
     * @param showArgumentInPrompt true to show (default: true)
     * @return this builder
     */
    public SubCommandModeSettingsBuilder showArgumentInPrompt(boolean showArgumentInPrompt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the context command name.
     * Set to null to disable.
     *
     * @param contextCommand the command name (default: "context")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder contextCommand(String contextCommand) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the message shown when entering sub-command mode.
     * Supports placeholder: {name}
     *
     * @param enterMessage the message (default: "Entering {name} mode.")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder enterMessage(String enterMessage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the message shown when exiting sub-command mode.
     * Supports placeholder: {name}
     *
     * @param exitMessage the message (default: null - no message)
     * @return this builder
     */
    public SubCommandModeSettingsBuilder exitMessage(String exitMessage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the exit hint message format.
     * Supports placeholders: {exit}, {alt}
     *
     * @param exitHint the hint format (default: "Type '{exit}' to return.")
     * @return this builder
     */
    public SubCommandModeSettingsBuilder exitHint(String exitHint) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set whether Ctrl+C should exit sub-command mode.
     *
     * @param exitOnCtrlC true to exit on Ctrl+C (default: true)
     * @return this builder
     */
    public SubCommandModeSettingsBuilder exitOnCtrlC(boolean exitOnCtrlC) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Build the SubCommandModeSettings instance.
     *
     * @return the configured settings
     */
    public SubCommandModeSettings build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
