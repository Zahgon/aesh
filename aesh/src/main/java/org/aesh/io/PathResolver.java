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
package org.aesh.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.aesh.terminal.utils.Config;

/**
 * Resolve a file that might contain (~,*,?) to its proper parentPath
 * Returns a list of files.
 *
 * @author Aesh team
 */
public class PathResolver {

    private static final char SEPARATOR = Config.getPathSeparator().charAt(0);

    private static final char TILDE = '~';

    private static final String TILDE_WITH_SEPARATOR = "~" + Config.getPathSeparator();

    private static final char STAR = '*';

    private static final char WILDCARD = '?';

    private static final String PARENT = "..";

    private static final String PARENT_WITH_SEPARATOR = ".." + Config.getPathSeparator();

    private static final String ROOT = Config.getPathSeparator();

    private static final String DRIVER_SEPARATOR = ":";

    private static final String CURRENT_WITH_SEPARATOR = "." + Config.getPathSeparator();

    private static final String SEPARATOR_WITH_CURRENT = Config.getPathSeparator() + ".";

    private static final String SEPARATOR_CURRENT_SEPARATOR = Config.getPathSeparator() + "." + Config.getPathSeparator();

    private static final String CURRENT = ".";

    private static final Pattern starPattern = Pattern.compile("[\\*]+");

    /**
     * 1. find the absolute root directory
     * 2. check for wildcards
     *
     * @param incPath
     * @param cwd
     * @return
     */
    @SuppressWarnings("IndexOfReplaceableByContains")
    public static List<File> resolvePath(File incPath, File cwd) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static List<File> parseWildcard(File incPath) {
        ArrayList<File> files = new ArrayList<>();
        int index = -1;
        while (incPath.toString().indexOf(STAR) > -1) {
        }
        return files;
    }

    private static List<File> findFiles(File incPath, String searchArgument, boolean findDirectory) {
        ArrayList<File> files = new ArrayList<>();
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**");
        /*
         * DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
         *
         * @Override
         * public boolean accept(Path entry) {
         * return matcher.matches(entry.getFileName());
         * }
         * };
         */
        if (starPattern.matcher(searchArgument).matches()) {
            try (DirectoryStream<Path> stream = findDirectory ? Files.newDirectoryStream(incPath.toPath(), new DirectoryFilter()) : Files.newDirectoryStream(incPath.toPath(), new FileFilter())) {
                for (Path p : stream) files.add(p.toFile());
                return files;
            } catch (IOException e) {
                // Return empty list on I/O error
            }
        } else {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(incPath.toPath(), searchArgument)) {
                if (findDirectory) {
                    for (Path p : stream) if (Files.isDirectory(p))
                        files.add(p.toFile());
                } else {
                    for (Path p : stream) files.add(p.toFile());
                }
                return files;
            } catch (IOException e) {
                // Return empty list on I/O error
            }
        }
        return files;
    }

    private static class DirectoryFilter implements DirectoryStream.Filter<Path> {

        @Override
        public boolean accept(Path entry) throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static class FileFilter implements DirectoryStream.Filter<Path> {

        @Override
        public boolean accept(Path entry) throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    //todo: path criteria need to check if separator is in the path
    private static PathCriteria parsePath(File path) {
        int starIndex = path.toString().indexOf(STAR);
        int wildcardIndex = path.toString().indexOf(WILDCARD);
        int index = (starIndex < wildcardIndex || wildcardIndex < 0) ? starIndex : wildcardIndex;
        if (index == 0 && path.toString().length() == 1)
            return new PathCriteria(String.valueOf(SEPARATOR), "", path.toString());
        else {
            int parentSeparatorIndex = index - 1;
            while (path.toString().charAt(parentSeparatorIndex) != SEPARATOR && parentSeparatorIndex > -1) parentSeparatorIndex--;
            int childSeparatorIndex = index + 1;
            if (childSeparatorIndex < path.toString().length())
                while (path.toString().charAt(childSeparatorIndex) != SEPARATOR && parentSeparatorIndex < path.toString().length()) childSeparatorIndex++;
            String parentPath = path.toString().substring(0, parentSeparatorIndex);
            String criteria = path.toString().substring(parentSeparatorIndex + 1, childSeparatorIndex);
            String childPath = path.toString().substring(childSeparatorIndex, path.toString().length());
            return new PathCriteria(parentPath, childPath, criteria);
        }
    }

    static class PathCriteria {

        private final String parentPath;

        private final String childPath;

        private final String criteria;

        PathCriteria(String parentPath, String childPath, String criteria) {
            this.parentPath = parentPath;
            this.childPath = childPath;
            this.criteria = criteria;
        }

        public String getParentPath() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getCriteria() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getChildPath() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
