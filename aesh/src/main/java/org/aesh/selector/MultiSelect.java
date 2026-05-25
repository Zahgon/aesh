/*
 * JBoss, Home of Professional Open Source
 * Copyright 2019 Red Hat Inc. and/or its affiliates and other contributors
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
package org.aesh.selector;

import static org.aesh.terminal.utils.ANSI.CURSOR_START;
import static org.aesh.terminal.utils.ANSI.MOVE_LINE_DOWN;
import static org.aesh.terminal.utils.ANSI.MOVE_LINE_UP;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.aesh.command.shell.Shell;
import org.aesh.terminal.Key;
import org.aesh.terminal.utils.ANSI;
import org.aesh.terminal.utils.Config;

public class MultiSelect {

    private final Shell shell;

    private boolean pagination;

    private final String message;

    private int maxDisplayedLines = 0;

    private List<SelectLine> lines;

    private Page page;

    private int focusLine = 0;

    public MultiSelect(Shell shell, List<String> defaultValues, String message) {
        this(shell, message);
        lines = new ArrayList<>(defaultValues.size());
        for (String value : defaultValues) lines.add(new SelectLine(value, shell.size().getWidth()));
        pagination = lines.size() + 1 > shell.size().getHeight();
    }

    public MultiSelect(Shell shell, String message) {
        this.shell = shell;
        this.message = message;
        maxDisplayedLines = shell.size().getHeight() - 1;
    }

    public void setLines(List<SelectLine> lines) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> doSelect() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void updateFocusLineGoingDown() {
        lines.get(focusLine).focus();
        focusLine += maxDisplayedLines;
        if (focusLine > lines.size() - 1)
            focusLine = lines.size() - 1;
        lines.get(focusLine).focus();
        displayPage(page.top(), maxDisplayedLines, focusLine);
    }

    private void updateFocusLineGoingUp() {
        lines.get(focusLine).focus();
        focusLine -= maxDisplayedLines;
        if (focusLine < 0)
            focusLine = 0;
        lines.get(focusLine).focus();
        displayPage(page.top(), maxDisplayedLines, focusLine);
    }

    private void moveDown(int move) {
        lines.get(focusLine).focus();
        focusLine += move;
        lines.get(focusLine).focus();
        shell.write(' ');
        shell.write(ANSI.moveRowsDown(move));
        shell.write(ANSI.CURSOR_START);
        shell.write('>');
        shell.write(ANSI.CURSOR_START);
    }

    private void moveDown() {
        lines.get(focusLine).focus();
        focusLine++;
        lines.get(focusLine).focus();
        shell.write(' ');
        shell.write(MOVE_LINE_DOWN);
        shell.write(ANSI.CURSOR_START);
        shell.write('>');
        shell.write(ANSI.CURSOR_START);
    }

    private void moveUp() {
        lines.get(focusLine).focus();
        focusLine--;
        lines.get(focusLine).focus();
        shell.write(' ');
        shell.write(MOVE_LINE_UP);
        shell.write(ANSI.CURSOR_START);
        shell.write('>');
        shell.write(ANSI.CURSOR_START);
    }

    private void moveUp(int move) {
        lines.get(focusLine).focus();
        focusLine -= move;
        lines.get(focusLine).focus();
        shell.write(' ');
        shell.write(ANSI.moveRowsUp(move));
        shell.write(ANSI.CURSOR_START);
        shell.write('>');
        shell.write(ANSI.CURSOR_START);
    }

    private int calcNumOfDisplayableLines() {
        if (maxDisplayedLines < lines.size())
            return maxDisplayedLines;
        else
            return lines.size();
    }

    private void displayPage(int startLine, int numberOfLines, int focusLine) {
        StringBuilder builder = new StringBuilder();
        builder.append(message + "  [Use arrow up/down to move and space to select. Enter to finish]");
        for (int i = startLine; i < (startLine + numberOfLines); i++) {
            builder.append(Config.getLineSeparator());
            builder.append(lines.get(i).print());
        }
        if (pagination)
            shell.write(ANSI.CLEAR_SCREEN);
        shell.write(builder.toString());
        shell.write(CURSOR_START);
        if (focusLine < startLine + numberOfLines - 1) {
            shell.write(ANSI.moveRowsUp(startLine + numberOfLines - focusLine - 1));
        }
    }

    private List<String> collectSelected() {
        return lines.stream().filter(SelectLine::isSelected).map(SelectLine::value).collect(Collectors.toList());
    }

    class Page {

        private int top;

        private int bottom;

        Page(int top, int bottom) {
            this.top = top;
            this.bottom = bottom;
        }

        public int top() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setTop(int top) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int bottom() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setBottom(int bottom) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
