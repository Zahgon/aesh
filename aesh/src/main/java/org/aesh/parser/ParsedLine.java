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
package org.aesh.parser;

import java.util.ArrayList;
import java.util.List;
import org.aesh.command.operator.OperatorType;

/**
 * A input line that is parsed into chunks of words.
 *
 * The input are split into words usually based on whitespaces.
 * The exceptions are made for escaped whitespaces and whitespaces that are
 * enclosed within quotes.
 * Single and double quotes is accepted, but only as pairs.
 *
 * If the cursor position is given, ParsedLine will be able to return the word
 * "connected" to the cursor.
 * If no cursor position is given the cursor value is -1 and cursorWord is an empty string.
 *
 * @author Aesh team
 */
public class ParsedLine {

    private final String originalInput;

    private final String errorMessage;

    private final List<ParsedWord> words;

    private final ParserStatus status;

    private final int cursor;

    private final int cursorWord;

    private final int wordCursor;

    private final OperatorType operator;

    public ParsedLine(String originalInput, List<ParsedWord> words, int cursor, int cursorWord, int wordCursor, ParserStatus status, String errorMessage, OperatorType operator) {
        this.originalInput = originalInput;
        this.cursor = cursor;
        this.cursorWord = cursorWord;
        this.wordCursor = wordCursor;
        this.status = status;
        this.errorMessage = errorMessage;
        this.operator = operator;
        if (words == null) {
            this.words = new ArrayList<>(0);
            return;
        }
        this.words = words;
    }

    /**
     * @return cursor
     */
    public int cursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the word index connected to the cursor
     */
    public int selectedIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the word connected to the cursor.
     *         If not cursor was given it will return an empty ParsedWord object.
     */
    public ParsedWord selectedWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the word connected to the cursor.
     *         If the cursor is not at the end of the word,
     *         it will only return part of the word up to the cursor position.
     */
    public ParsedWord selectedWordToCursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return index inside the word where the cursor is positioned.
     */
    public int wordCursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return original input
     */
    public String line() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return any errors that was found during parsing
     */
    public String errorMessage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return the list of words
     */
    public List<ParsedWord> words() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return status of the parser. Useful if there have been any errors.
     */
    public ParserStatus status() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedWord lastWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedWord firstWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasWords() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return a highly specialized iterator to make it easier to parse the input
     */
    public ParsedLineIterator iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OperatorType operator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean cursorAtEnd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean spaceAtEnd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCursorAtEndOfSelectedWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
