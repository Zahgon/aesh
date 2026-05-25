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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.aesh.command.Command;
import org.aesh.command.impl.internal.ProcessedCommand;
import org.aesh.command.impl.internal.ProcessedOption;
import org.aesh.command.impl.parser.CommandLineParser;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.populator.CommandPopulator;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.console.AeshContext;
import org.aesh.selector.SelectorType;

/**
 * Populator for MapCommand.
 *
 * @author Aesh team
 */
public class MapCommandPopulator<O extends Object, CI extends CommandInvocation> implements CommandPopulator<O, CI> {

    private final MapCommand<CI> instance;

    private final Map<String, String> unknownOptions = new HashMap<>();

    MapCommandPopulator(MapCommand<CI> instance) {
        Objects.requireNonNull(instance);
        this.instance = instance;
    }

    @Override
    public void populateObject(ProcessedCommand<Command<CI>, CI> processedCommand, InvocationProviders invocationProviders, AeshContext aeshContext, CommandLineParser.Mode validate) throws CommandLineParserException, OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public O getObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addUnknownOption(String opt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
