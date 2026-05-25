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
package org.aesh.command.man;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TerminalPage parse files or input string and prepare it to be displayed in a term
 *
 * @author Aesh team
 */
public class TerminalPage {

    private List<String> lines;

    private FileParser fileParser;

    public TerminalPage(FileParser fileParser, int columns) throws IOException {
        this.fileParser = fileParser;
        lines = fileParser.loadPage(columns);
    }

    public String getLine(int num) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<Integer> findWord(String word) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getFileName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getLines() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public enum Search {

        SEARCHING, RESULT, NOT_FOUND, NO_SEARCH
    }
}
