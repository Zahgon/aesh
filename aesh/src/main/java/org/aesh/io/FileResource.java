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
package org.aesh.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import org.aesh.io.filter.ResourceFilter;

/**
 * Default impl of Resource, using java.io.File
 *
 * @author Aesh team
 */
public class FileResource implements Resource {

    private File file;

    public FileResource(File file) {
        if (file == null)
            throw new IllegalArgumentException("file argument cant be null");
        this.file = file;
    }

    public FileResource(Path file) {
        if (file == null)
            throw new IllegalArgumentException("file argument cant be null");
        this.file = file.toFile();
    }

    public FileResource(String file) {
        if (file == null)
            throw new IllegalArgumentException("file argument cant be null");
        this.file = new File(file);
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getAbsolutePath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isLeaf() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isDirectory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isSymbolicLink() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource readSymbolicLink() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean exists() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean mkdirs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void move(Resource target) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource getParent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<Resource> list() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<Resource> list(ResourceFilter filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<Resource> listRoots() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<Resource> resolve(Resource cwd) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public InputStream read() throws FileNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OutputStream write(boolean append) throws FileNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource newInstance(String path) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Resource copy(Resource destination) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean setLastModified(long time) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long lastModified() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setLastAccessed(long time) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long lastAccessed() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public File getFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
