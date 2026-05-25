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
package org.aesh.graphics;

import org.aesh.terminal.Connection;
import org.aesh.terminal.formatting.TerminalColor;
import org.aesh.terminal.formatting.TerminalTextStyle;
import org.aesh.terminal.tty.Capability;
import org.aesh.terminal.tty.Size;
import org.aesh.terminal.utils.ANSI;

/**
 * @author Aesh team
 * @author Aesh team
 */
public class AeshGraphics implements Graphics {

    private static final String CURSOR_DOWN = ANSI.START + "1B" + ANSI.START + "1D";

    private final Connection connection;

    private final GraphicsConfiguration graphicsConfiguration;

    private TerminalColor currentColor;

    private TerminalTextStyle currentStyle;

    AeshGraphics(Connection connection, GraphicsConfiguration graphicsConfiguration) {
        this.connection = connection;
        this.graphicsConfiguration = graphicsConfiguration;
        currentColor = new TerminalColor();
        connection.put(Capability.cursor_invisible);
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAndShowCursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public TerminalColor getColor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setColor(TerminalColor color) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public TerminalTextStyle getTextStyle() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setTextStyle(TerminalTextStyle textStyle) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void drawString(String str, int x, int y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void drawCircle(int x0, int y0, int radius) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void printColor(TerminalColor color) {
        if (color != null)
            connection.write(color.fullString());
    }

    private void drawPixel(int x, int y) {
        connection.put(Capability.cursor_address, y, x);
        connection.write("x");
    }

    private void drawHorizontalLine(int x, int y, int width) {
        Size terminalSize = graphicsConfiguration.getBounds();
        if (terminalSize.getHeight() > y && terminalSize.getWidth() > y) {
            if (terminalSize.getWidth() < x + width)
                width = terminalSize.getWidth() - x - 1;
            connection.put(Capability.cursor_address, y, x);
            char[] line = new char[width];
            for (int i = 0; i < line.length; i++) {
                line[i] = (i == 0 || i == line.length - 1) ? 'x' : '-';
            }
            connection.write(new String(line));
        }
    }

    private void drawVerticalLine(int x, int y, int length) {
        Size terminalSize = graphicsConfiguration.getBounds();
        if (terminalSize.getHeight() > y && terminalSize.getWidth() > y) {
            if (terminalSize.getHeight() < y + length)
                length = terminalSize.getHeight() - y - 1;
            connection.put(Capability.cursor_address, y, x);
            for (int i = 0; i < length; i++) {
                connection.write("|");
                connection.write(CURSOR_DOWN);
            }
        }
    }
}
