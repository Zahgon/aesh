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
package org.aesh.command.export;

import org.aesh.parser.LineParser;
import org.aesh.readline.completion.CompleteOperation;
import org.aesh.readline.completion.Completion;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class ExportCompletion implements Completion {

    private static final String EXPORT = "export";

    private static final String EXPORT_SPACE = "export ";

    private final ExportManager exportManager;

    private final LineParser lineParser;

    public ExportCompletion(ExportManager manager) {
        this.exportManager = manager;
        lineParser = new LineParser();
    }

    @Override
    public void complete(CompleteOperation completeOperation) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
