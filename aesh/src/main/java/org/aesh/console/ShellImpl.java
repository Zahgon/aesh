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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.aesh.command.shell.Shell;
import org.aesh.readline.Readline;
import org.aesh.readline.action.ActionDecoder;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.Attributes;
import org.aesh.terminal.Connection;
import org.aesh.terminal.Key;
import org.aesh.terminal.tty.Capability;
import org.aesh.terminal.tty.Size;
import org.aesh.terminal.utils.Config;

/**
 * @author Aesh team
 */
public class ShellImpl implements Shell {

    private Connection connection;

    private final PagingSupport pagingSupport;

    public ShellImpl(Connection connection) {
        this(connection, false);
    }

    public ShellImpl(Connection connection, boolean search) {
        this.connection = connection;
        pagingSupport = new PagingSupport(connection, search);
    }

    void startCollectOutput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // handle "a la" 'more' scrolling
    // Doesn't take into account wrapped lines (lines that are longer than the
    // terminal width. This could make a page to skip some lines.
    void printCollectedOutput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(String msg, boolean page) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writeln(String msg, boolean page) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(char out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String readLine() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String readLine(Prompt prompt) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Key read(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Key read() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Key doRead(long timeout, TimeUnit unit) throws InterruptedException {
        printCollectedOutput();
        pagingSupport.reset();
        ActionDecoder decoder = new ActionDecoder();
        final Key[] key = { null };
        CountDownLatch latch = new CountDownLatch(1);
        Attributes attributes = connection.enterRawMode();
        try {
            connection.setStdinHandler(keys -> {
                decoder.add(keys);
                if (decoder.hasNext()) {
                    key[0] = Key.findStartKey(decoder.next().buffer().array());
                    latch.countDown();
                }
            });
            try {
                // Wait until interrupted
                if (unit == null)
                    latch.await();
                else
                    latch.await(timeout, unit);
            } finally {
                connection.setStdinHandler(null);
            }
        } finally {
            connection.setAttributes(attributes);
        }
        return key[0];
    }

    @Override
    public Key read(Prompt prompt) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enableAlternateBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enableMainBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Size size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Connection connection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
