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
package org.aesh.command.impl.completer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.aesh.command.Command;
import org.aesh.command.completer.CompleterInvocation;
import org.aesh.console.AeshContext;
import org.aesh.terminal.formatting.TerminalString;

/**
 * A payload object to store completion values for an Option
 * Offset is only needed to change if the there is only one completion value
 * and the value is not replacing the current given value, but appending.
 * If its only appending then set the offset to the length of completeValue
 * given in OptionCompleter.complete(String completeValue)
 *
 * @author Aesh team
 */
public class CompleterData implements CompleterInvocation {

    private List<TerminalString> completerValues;

    private boolean appendSpace = true;

    private final String completeValue;

    private final Command command;

    private final AeshContext aeshContext;

    private int offset = -1;

    private boolean ignoreOffset = false;

    private boolean ignoreStartsWith = false;

    public CompleterData(AeshContext aeshContext, String completeValue, Command command) {
        this.aeshContext = aeshContext;
        this.completeValue = completeValue;
        this.command = command;
        completerValues = new ArrayList<>();
    }

    @Override
    public String getGivenCompleteValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Command getCommand() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<TerminalString> getCompleterValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setCompleterValues(Collection<String> completerValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setCompleterValuesTerminalString(List<TerminalString> completerValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clearCompleterValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void addAllCompleterValues(Collection<String> completerValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void addCompleterValue(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void addCompleterValueTerminalString(TerminalString value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isAppendSpace() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setAppendSpace(boolean appendSpace) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean doIgnoreOffset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setIgnoreOffset(boolean ignoreOffset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setOffset(int offset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getOffset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setIgnoreStartsWith(boolean ignoreStartsWith) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isIgnoreStartsWith() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public AeshContext getAeshContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
