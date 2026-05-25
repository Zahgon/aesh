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
import java.util.List;
import java.util.logging.Logger;
import org.aesh.command.Command;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.shell.Shell;
import org.aesh.terminal.Key;
import org.aesh.terminal.KeyAction;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Config;
import org.aesh.terminal.utils.LoggerUtil;

/**
 * An abstract command used to display files
 * Implemented similar to less
 *
 * @author Aesh team
 */
public abstract class AeshFileDisplayer implements Command {

    private int rows;

    private int columns;

    private int topVisibleRow;

    //only rewrite page if rowCache != row
    private int topVisibleRowCache;

    private TerminalPage page;

    private StringBuilder number;

    private TerminalPage.Search search = TerminalPage.Search.NO_SEARCH;

    private StringBuilder searchBuilder;

    private List<Integer> searchLines;

    private static final Logger LOGGER = LoggerUtil.getLogger(AeshFileDisplayer.class.getName());

    private CommandInvocation commandInvocation;

    private boolean stop;

    public AeshFileDisplayer() {
        stop = false;
    }

    protected void setCommandInvocation(CommandInvocation commandInvocation) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected CommandInvocation getCommandInvocation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Shell getShell() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void afterAttach() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void afterDetach() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void processInput() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void processOperation(Key operation) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void display() throws IOException {
        if (topVisibleRow != topVisibleRowCache) {
            getShell().clear();
            if (search == TerminalPage.Search.RESULT && searchLines.size() > 0) {
                String searchWord = searchBuilder.toString();
                for (int i = topVisibleRow; i < (topVisibleRow + rows - 1); i++) {
                    if (i < page.size()) {
                        String line = page.getLine(i);
                        if (line.contains(searchWord))
                            displaySearchLine(line, searchWord);
                        else
                            getShell().write(line);
                        getShell().write(Config.getLineSeparator());
                    }
                }
                topVisibleRowCache = topVisibleRow;
            } else {
                for (int i = topVisibleRow; i < (topVisibleRow + rows - 1); i++) {
                    if (i < page.size()) {
                        getShell().write(page.getLine(i) + Config.getLineSeparator());
                    }
                }
                topVisibleRowCache = topVisibleRow;
            }
            displayBottom();
        }
    }

    /**
     * highlight the specific word thats found in the search
     */
    private void displaySearchLine(String line, String searchWord) throws IOException {
        int start = line.indexOf(searchWord);
        getShell().write(line.substring(0, start));
        getShell().write(ANSI.INVERT_BACKGROUND);
        getShell().write(searchWord);
        getShell().write(ANSI.RESET);
        getShell().write(line.substring(start + searchWord.length(), line.length()));
    }

    public abstract FileParser getFileParser();

    public abstract void displayBottom() throws IOException;

    public void writeToConsole(String word) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearBottomLine() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isAtBottom() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isAtTop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TerminalPage.Search getSearchStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getSearchWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getTopVisibleRow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void findSearchWord(boolean forward) throws IOException {
        LOGGER.info("searching for: " + searchBuilder.toString());
        searchLines = page.findWord(searchBuilder.toString());
        LOGGER.info("found: " + searchLines);
        if (searchLines.size() > 0) {
            for (Integer i : searchLines) if (i > topVisibleRow) {
                topVisibleRow = i - 1;
                display();
                return;
            }
        } else {
            search = TerminalPage.Search.NOT_FOUND;
            displayBottom();
        }
    }

    /**
     * number written by the user (used to jump to specific commands)
     */
    private int getNumber() {
        if (number.length() > 0)
            return Integer.parseInt(number.toString());
        else
            return 1;
    }

    private void clearNumber() {
        number = new StringBuilder();
    }

    private enum Background {

        NORMAL, INVERSE
    }
}
