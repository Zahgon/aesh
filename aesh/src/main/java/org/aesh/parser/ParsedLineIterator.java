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

/**
 * A specialized iterator that makes it easier to parse the input.
 *
 * Polling either words or chars from the stack will update the respective
 * other values correctly as well.
 *
 * @author Aesh team
 */
public class ParsedLineIterator {

    private final ParsedLine parsedLine;

    private int word = 0;

    private int character = 0;

    public ParsedLineIterator(ParsedLine parsedLine) {
        this.parsedLine = parsedLine;
    }

    public String originalLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return true if there is a next word
     */
    public boolean hasNextWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return true if there is a next char
     */
    public boolean hasNextChar() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Polls the next ParsedWord from the stack.
     *
     * @return next ParsedWord
     */
    public ParsedWord pollParsedWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Peeks at the next ParsedWord from the stack
     *
     * @return next ParsedWord
     */
    public ParsedWord peekParsedWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Polls the next word as String from the stack.
     *
     * @return next word
     */
    public String pollWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Peeks the next word as String from the stack.
     *
     * @return next word
     */
    public String peekWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Polls the next char from the stack
     *
     * @return next char
     */
    public char pollChar() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Peeks at the next char from the stack
     *
     * @return next char
     */
    public char peekChar() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return true if there are no more words/chars on the stack
     */
    public boolean finished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return any parsing errors made when creating the ParsedLine
     */
    public String parserError() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a substring of the base input from where the current position is.
     *
     * @return substring from current position till the end.
     */
    public String stringFromCurrentPosition() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Update the current position with specified length.
     * The input will append to the current position of the iterator.
     *
     * @param length update length
     */
    public void updateIteratorPosition(int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @return true if next word is connected to the cursor
     */
    public boolean isNextWordCursorWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCurrentWordCursorWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ParsedLine baseLine() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean pastCursorWord() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
