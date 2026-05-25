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
package org.aesh.command.man.parser;

import java.util.ArrayList;
import java.util.List;
import org.aesh.terminal.utils.Config;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class ManParameter {

    final List<String> out = new ArrayList<>();

    private static final String argPad = "  ";

    private static final String textPad = "    ";

    /**
     * First line is the param/option name
     * following lines are the description
     */
    public ManParameter parseParams(List<String> input, int columns) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getAsList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String printToTerminal() {
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
