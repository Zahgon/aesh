package org.aesh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesLookup {

    public static final Pattern systemProperties = Pattern.compile("^\\$\\{((env:)|(sys:))?((\\.*\\w+)+)\\}$");

    public static List<String> checkForSystemVariables(List<String> defaultValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String findEnvironmentVariable(String variable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String findSystemProperty(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
