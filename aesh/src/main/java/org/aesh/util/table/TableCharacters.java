/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
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
package org.aesh.util.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.aesh.terminal.utils.ANSI;

/**
 * Constants and utility methods for table border characters.
 *
 * <pre>
 *   HTL HBH HTI HBH HTR
 *   HBV  h  HCS  h  HBV
 *   TTL TTH TTI TTH TTR
 *   TBV  v  TCS  v  TBV
 *   RSL RSH RSI RSH RSR
 *   TBV  v  TBS  v  TBV
 *   TBL TBH TBI TBH TBR
 *
 *   minimum requirements are HORIZONTAL, VERTICAL, and INTERSECT
 *   HORIZONTAL -> TTH
 *   VERTICAL -> HCS, TCS
 *   INTERSECT -> TTI
 * </pre>
 */
public final class TableCharacters {

    // Header top border
    public static final String HEADER_TOP_LEFT = "HTL";

    public static final String HEADER_TOP_RIGHT = "HTR";

    public static final String HEADER_TOP_INTERSECT = "HTI";

    // Header column separator and border
    public static final String HEADER_COLUMN_SEPARATOR = "HCS";

    public static final String HEADER_BORDER_VERTICAL = "HBV";

    public static final String HEADER_BORDER_HORIZONTAL = "HBH";

    // Table top border
    public static final String TABLE_TOP_LEFT = "TTL";

    public static final String TABLE_TOP_RIGHT = "TTR";

    public static final String TABLE_TOP_HORIZONTAL = "TTH";

    public static final String TABLE_TOP_INTERSECT = "TTI";

    // Table body
    public static final String TABLE_BORDER_VERTICAL = "TBV";

    public static final String TABLE_BORDER_HORIZONTAL = "TBH";

    public static final String TABLE_COLUMN_SEPARATOR = "TCS";

    // Row separator
    public static final String ROW_SEPARATOR_LEFT = "RSL";

    public static final String ROW_SEPARATOR_HORIZONTAL = "RSH";

    public static final String ROW_SEPARATOR_INTERSECT = "RSI";

    public static final String ROW_SEPARATOR_RIGHT = "RSR";

    // Table bottom border
    public static final String TABLE_BOTTOM_LEFT = "TBL";

    public static final String TABLE_BOTTOM_RIGHT = "TBR";

    public static final String TABLE_BOTTOM_INTERSECT = "TBI";

    public static final String TABLE_BOTTOM_HORIZONTAL = "TBH";

    // Shorthand constants
    public static final String VERTICAL = "V";

    public static final String HORIZONTAL = "H";

    public static final String INTERSECT = "X";

    private TableCharacters() {
    }

    /**
     * Expands shorthand V/H/X entries to their full position names.
     *
     * @param map the character map potentially containing shorthand keys
     * @param border whether to also fill border positions
     * @return a new map with shorthands expanded to full position names
     */
    public static Map<String, String> convertToFullNames(Map<String, String> map, boolean border) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses a visual template string into a character map.
     * <p>
     * Each line of the template must be exactly 5 characters, representing a row of the
     * table border in the format: {@code [left][fill][intersect][fill][right]}.
     * Characters at positions 0, 1, 2, and 4 are extracted (position 3 is typically
     * the same as position 1 and is ignored).
     * <p>
     * Supports 3-line (no outer border), 5-line (with outer border), or 6-line
     * (with outer border and row separators) templates.
     *
     * @param template a multi-line visual template string
     * @param defaultMap fallback map if the template is invalid
     * @return a map of position names to border characters
     */
    public static Map<String, String> templateToMap(String template, Map<String, String> defaultMap) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wraps each character value in the map with an ANSI prefix and RESET suffix.
     *
     * @param prefix the ANSI escape prefix (e.g. {@code ANSI.RED_TEXT})
     * @param map the character map to prefix
     * @return a new map with prefixed values
     */
    public static Map<String, String> prefix(String prefix, Map<String, String> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the character map defines a complete outside border.
     */
    public static boolean hasOutsideBorder(Map<String, String> characters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the character map defines row separators.
     */
    public static boolean hasRowSeparator(Map<String, String> characters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the character map has the minimum required entries for rendering a table.
     */
    public static boolean isValid(Map<String, String> characters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Splits a list of potentially multi-line header strings into aligned rows.
     *
     * <pre>
     * ["foo\nfoo","bar"] -&gt; [["foo","bar"], ["foo",""]]
     * </pre>
     *
     * @param input list of header strings, potentially containing line separators
     * @return list of rows, each row being a list of strings
     */
    public static List<List<String>> lineSplit(List<String> input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
