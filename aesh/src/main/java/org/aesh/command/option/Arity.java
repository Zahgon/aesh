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
package org.aesh.command.option;

/**
 * Represents arity constraints for positional arguments.
 * Specifies the minimum and maximum number of values an argument accepts.
 *
 * <p>
 * Supported string formats:
 * </p>
 * <ul>
 * <li>{@code "2"} — exactly 2 values</li>
 * <li>{@code "0..1"} — optional single value</li>
 * <li>{@code "1..*"} — one or more values</li>
 * <li>{@code "0..*"} — zero or more values (default for @Arguments)</li>
 * <li>{@code "2..4"} — between 2 and 4 values</li>
 * </ul>
 *
 * @author Aesh team
 */
public class Arity {

    /**
     * Represents unlimited maximum.
     */
    public static final int UNLIMITED = Integer.MAX_VALUE;

    private final int min;

    private final int max;

    public Arity(int min, int max) {
        if (min < 0)
            throw new IllegalArgumentException("Arity min must be >= 0, was: " + min);
        if (max < min)
            throw new IllegalArgumentException("Arity max (" + max + ") must be >= min (" + min + ")");
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getMax() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isUnlimited() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parse an arity string.
     *
     * @param arity the arity string (e.g., "2", "0..1", "1..*")
     * @return the parsed Arity, or null if the input is null or empty
     * @throws IllegalArgumentException if the format is invalid
     */
    public static Arity parse(String arity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
