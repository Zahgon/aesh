/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * as indicated by the @authors tag
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.aesh.command.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.aesh.command.Command;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.parser.ParsedLine;

/**
 * A command that stores option values in a map.
 *
 * @author Aesh team
 */
public abstract class MapCommand<T extends CommandInvocation> implements Command<T> {

    private final Map<String, Object> values = new HashMap<>();

    public Object getValue(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setValue(String name, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void resetValue(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean contains(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<String, Object> getValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean checkForRequiredOptions(ParsedLine pl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void resetAll() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
