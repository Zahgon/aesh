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
package org.aesh.console;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.Execution;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.parser.CommandLineParserException;
import org.aesh.command.validator.CommandValidatorException;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.terminal.Connection;
import org.aesh.terminal.tty.Signal;
import org.aesh.terminal.utils.Config;
import org.aesh.terminal.utils.LoggerUtil;

/**
 * @author Aesh team
 */
public class Process extends Thread implements Consumer<Signal> {

    private final Connection conn;

    private final Execution<? extends CommandInvocation> execution;

    private final ProcessManager manager;

    private volatile boolean running;

    private static final Logger LOGGER = LoggerUtil.getLogger(Process.class.getName());

    private int pid;

    public Process(ProcessManager manager, Connection conn, Execution<? extends CommandInvocation> execution) {
        this.manager = manager;
        this.conn = conn;
        this.execution = execution;
    }

    @Override
    public void accept(Signal signal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Execution<? extends CommandInvocation> execution() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int pid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
