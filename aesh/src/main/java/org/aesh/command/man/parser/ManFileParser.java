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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.aesh.command.man.FileParser;
import org.aesh.terminal.utils.Config;

/**
 * Read a asciidoc file and parse it to something that can be
 * displayed nicely in a terminal.
 *
 * @author Aesh team
 */
public class ManFileParser implements FileParser {

    private final List<ManSection> sections;

    private String name;

    private InputStreamReader reader;

    public ManFileParser() {
        sections = new ArrayList<ManSection>();
    }

    public void setInput(InputStream input) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> loadPage(int columns) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void processHeader(List<String> header, int columns) throws IOException {
        if (header.size() != 4)
            throw new IOException("File did not include the correct header.");
        name = header.get(0);
        if (!header.get(2).equals(":doctype: manpage"))
            throw new IOException("File did not include the correct header: \":doctype: manpage\"");
    }

    public List<ManSection> getSections() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getAsList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String print() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
