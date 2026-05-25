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
package org.aesh.io.scanner;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * {@code ClassFileBuffer} is used by {@link AnnotationDetector} to efficiently read Java
 * ClassFile files from an {@link InputStream} and parse the content via the {@link DataInput}
 * interface.
 * <p>
 * Note that Java ClassFile files can grow really big,
 * {@code com.sun.corba.se.impl.logging.ORBUtilSystemException} is 128.2 kb!
 *
 * @author Aesh team
 */
final class ClassFileBuffer implements DataInput {

    private byte[] buffer;

    // the number of significant bytes read
    private int size;

    // the "read pointer"
    private int pointer;

    /**
     * Create a new, empty {@code ClassFileBuffer} with the default initial capacity (8 kb).
     */
    ClassFileBuffer() {
        this(8 * 1024);
    }

    /**
     * Create a new, empty {@code ClassFileBuffer} with the specified initial capacity.
     * The initial capacity must be greater than zero. The internal buffer will grow
     * automatically when a higher capacity is required. However, buffer resizing occurs
     * extra overhead. So in good initial capacity is important in performance critical
     * situations.
     */
    ClassFileBuffer(final int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity < 1: " + initialCapacity);
        }
        this.buffer = new byte[initialCapacity];
    }

    /**
     * Clear and fill the buffer of this {@code ClassFileBuffer} with the
     * supplied byte stream.
     * The read pointer is reset to the start of the byte array.
     */
    public void readFrom(final InputStream in) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the file-pointer offset, measured from the beginning of this file,
     * at which the next read or write occurs.
     */
    public void seek(final int position) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the size (in bytes) of this Java ClassFile file.
     */
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // DataInput
    @Override
    public void readFully(final byte[] bytes) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void readFully(final byte[] bytes, final int offset, final int length) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int skipBytes(final int n) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte readByte() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean readBoolean() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int readUnsignedByte() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int readUnsignedShort() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public short readShort() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public char readChar() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int readInt() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long readLong() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public float readFloat() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public double readDouble() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This methods throws an {@link UnsupportedOperationException} because the method
     * is deprecated and not used in the context of this implementation.
     *
     * @deprecated Does not support UTF-8, use readUTF() instead
     */
    @Override
    @Deprecated
    public String readLine() throws IOException {
        throw new UnsupportedOperationException("readLine() is deprecated and not supported");
    }

    @Override
    public String readUTF() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // private
    private int read() {
        return buffer[pointer++] & 0xff;
    }

    private void resizeIfNeeded() {
        if (size >= buffer.length) {
            final byte[] newBuffer = new byte[buffer.length * 2];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            buffer = newBuffer;
        }
    }
}
