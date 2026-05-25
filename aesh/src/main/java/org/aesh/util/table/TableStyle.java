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

import static org.aesh.util.table.TableCharacters.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Predefined table border styles.
 */
public enum TableStyle {

    /**
     * Simple ASCII style without outside borders: {@code |}, {@code -}, {@code +}
     */
    POSTGRES {

        @Override
        public Map<String, String> characters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * ASCII style with full borders: {@code |}, {@code -}, {@code +}
     */
    SQLITE {

        @Override
        public Map<String, String> characters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Unicode box-drawing style with outside borders.
     */
    DUCKDB {

        @Override
        public Map<String, String> characters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Plain style with no borders or separators — columns separated by spaces only.
     */
    PLAIN {

        @Override
        public Map<String, String> characters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Double-line box-drawing style with outside borders and row separators.
     */
    DOUBLE {

        @Override
        public Map<String, String> characters() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ;

    /**
     * Returns the character map for this style.
     */
    public abstract Map<String, String> characters();
}
