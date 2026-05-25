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
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesh.tamboui;

import java.nio.charset.Charset;
import java.util.function.Consumer;
import org.aesh.terminal.Attributes;
import org.aesh.terminal.Connection;
import org.aesh.terminal.Device;
import org.aesh.terminal.tty.Capability;
import org.aesh.terminal.tty.Signal;
import org.aesh.terminal.tty.Size;

/**
 * A Connection wrapper that delegates everything to the underlying connection
 * but makes {@link #close()} a no-op. This prevents TamboUI's
 * {@code AeshBackend.close()} from closing the aesh Connection, which would
 * terminate the aesh session.
 *
 * @author Aesh team
 */
class NonClosingConnection implements Connection {

    private final Connection delegate;

    NonClosingConnection(Connection delegate) {
        this.delegate = delegate;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close(int exit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Device device() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Size size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<Size> getSizeHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSizeHandler(Consumer<Size> handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<Signal> getSignalHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSignalHandler(Consumer<Signal> handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<int[]> getStdinHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setStdinHandler(Consumer<int[]> handler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<int[]> stdoutHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setCloseHandler(Consumer<Void> closeHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Consumer<Void> getCloseHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void openBlocking() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void openNonBlocking() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean reading() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean put(Capability capability, Object... params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Attributes getAttributes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setAttributes(Attributes attr) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Charset inputEncoding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Charset outputEncoding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean supportsAnsi() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
