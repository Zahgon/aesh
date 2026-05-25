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
package org.aesh.command.impl.internal;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.aesh.command.activator.OptionActivator;
import org.aesh.command.completer.OptionCompleter;
import org.aesh.command.converter.Converter;
import org.aesh.command.impl.converter.AeshConverterInvocation;
import org.aesh.command.impl.parser.AeshOptionParser;
import org.aesh.command.impl.validator.AeshValidatorInvocation;
import org.aesh.command.invocation.InvocationProviders;
import org.aesh.command.parser.OptionParser;
import org.aesh.command.parser.OptionParserException;
import org.aesh.command.renderer.OptionRenderer;
import org.aesh.command.validator.OptionValidator;
import org.aesh.command.validator.OptionValidatorException;
import org.aesh.console.AeshContext;
import org.aesh.io.Resource;
import org.aesh.selector.SelectorType;
import org.aesh.terminal.formatting.TerminalString;
import org.aesh.terminal.utils.ANSI;
import org.aesh.util.PropertiesLookup;

/**
 * @author Aesh team
 */
public final class ProcessedOption {

    private String shortName;

    private String name;

    private String description;

    private List<String> values;

    private String argument;

    private List<String> defaultValues;

    private Class<?> type;

    private Converter converter;

    private OptionType optionType;

    private boolean required = false;

    private char valueSeparator;

    private String fieldName;

    private String paramLabel;

    private org.aesh.command.option.Arity arity;

    private org.aesh.command.option.IndexRange indexRange;

    private OptionCompleter completer;

    private Map<String, String> properties;

    private boolean longNameUsed = true;

    private OptionValidator validator;

    private boolean endsWithSeparator = false;

    private OptionActivator activator;

    private OptionRenderer renderer;

    private boolean overrideRequired = false;

    private boolean ansiMode = true;

    private ProcessedCommand parent;

    private OptionParser parser;

    private boolean cursorOption = false;

    private boolean cursorValue = false;

    private boolean askIfNotSet = false;

    private boolean acceptNameWithoutDashes = false;

    private final SelectorType selectorType;

    private boolean negatable = false;

    private String negationPrefix = "no-";

    private boolean negatedByUser = false;

    private boolean optionalValue = false;

    private String fallbackValue;

    private boolean inherited = false;

    private String descriptionUrl;

    private boolean isUrl = false;

    private List<String> aliases = Collections.emptyList();

    private String helpGroup = "";

    private List<String> exclusiveWith = Collections.emptyList();

    private List<String> allowedValues = Collections.emptyList();

    private org.aesh.command.option.OptionVisibility visibility = org.aesh.command.option.OptionVisibility.BRIEF;

    private int order = Integer.MAX_VALUE;

    private int declarationOrder = Integer.MAX_VALUE;

    private BiConsumer<Object, Object> fieldSetter;

    private Consumer<Object> fieldResetter;

    private java.util.function.Function<Object, Object> fieldGetter;

    private FieldAccessor fieldAccessor;

    private Object initialValue;

    private boolean initialValueCaptured;

    private String mixinFieldName;

    private Field cachedField;

    private Class<?> cachedFieldClass;

    public ProcessedOption(char shortName, String name, String description, String argument, boolean required, char valueSeparator, boolean askIfNotSet, boolean acceptNameWithoutDashes, SelectorType selectorType, List<String> defaultValue, Class<?> type, String fieldName, OptionType optionType, Converter converter, OptionCompleter completer, OptionValidator optionValidator, OptionActivator activator, OptionRenderer renderer, OptionParser parser, boolean overrideRequired, boolean negatable, String negationPrefix, boolean inherited) throws OptionParserException {
        this(shortName, name, description, argument, required, valueSeparator, askIfNotSet, acceptNameWithoutDashes, selectorType, defaultValue, type, fieldName, optionType, converter, completer, optionValidator, activator, renderer, parser, overrideRequired, negatable, negationPrefix, inherited, null, false, false);
    }

    public ProcessedOption(char shortName, String name, String description, String argument, boolean required, char valueSeparator, boolean askIfNotSet, boolean acceptNameWithoutDashes, SelectorType selectorType, List<String> defaultValue, Class<?> type, String fieldName, OptionType optionType, Converter converter, OptionCompleter completer, OptionValidator optionValidator, OptionActivator activator, OptionRenderer renderer, OptionParser parser, boolean overrideRequired, boolean negatable, String negationPrefix, boolean inherited, String descriptionUrl, boolean isUrl) throws OptionParserException {
        this(shortName, name, description, argument, required, valueSeparator, askIfNotSet, acceptNameWithoutDashes, selectorType, defaultValue, type, fieldName, optionType, converter, completer, optionValidator, activator, renderer, parser, overrideRequired, negatable, negationPrefix, inherited, descriptionUrl, isUrl, false);
    }

    public ProcessedOption(char shortName, String name, String description, String argument, boolean required, char valueSeparator, boolean askIfNotSet, boolean acceptNameWithoutDashes, SelectorType selectorType, List<String> defaultValue, Class<?> type, String fieldName, OptionType optionType, Converter converter, OptionCompleter completer, OptionValidator optionValidator, OptionActivator activator, OptionRenderer renderer, OptionParser parser, boolean overrideRequired, boolean negatable, String negationPrefix, boolean inherited, String descriptionUrl, boolean isUrl, boolean optionalValue) throws OptionParserException {
        if (shortName != '\u0000')
            this.shortName = String.valueOf(shortName);
        this.name = name;
        this.description = description;
        this.argument = argument;
        this.required = required;
        this.valueSeparator = valueSeparator;
        this.type = type;
        this.fieldName = fieldName;
        this.overrideRequired = overrideRequired;
        this.optionType = optionType;
        this.converter = converter;
        this.completer = completer;
        this.validator = optionValidator;
        this.activator = activator;
        this.askIfNotSet = askIfNotSet;
        this.acceptNameWithoutDashes = acceptNameWithoutDashes;
        if (selectorType != null)
            this.selectorType = selectorType;
        else
            this.selectorType = SelectorType.NO_OP;
        this.parser = parser;
        if (renderer != null)
            this.renderer = renderer;
        this.defaultValues = PropertiesLookup.checkForSystemVariables(defaultValue);
        this.negatable = negatable;
        this.negationPrefix = negationPrefix != null ? negationPrefix : "no-";
        this.inherited = inherited;
        this.descriptionUrl = descriptionUrl;
        this.isUrl = isUrl || "java.net.URL".equals(type.getName()) || "java.net.URI".equals(type.getName());
        this.optionalValue = optionalValue;
        properties = java.util.Collections.emptyMap();
        values = java.util.Collections.emptyList();
    }

    /**
     * Direct factory for generated (annotation-processor) code. Bypasses
     * ProcessedOptionBuilder, PropertiesLookup regex, URL type inference,
     * and all validation — those are resolved at compile time.
     */
    public static ProcessedOption createDirect(String shortName, String name, String description, Class<?> type, String fieldName, OptionType optionType, Converter converter, FieldAccessor fieldAccessor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * No-arg constructor for createDirect(). Sets only immutable defaults.
     */
    private ProcessedOption() {
        this.selectorType = SelectorType.NO_OP;
        this.valueSeparator = ' ';
        this.properties = Collections.emptyMap();
        this.values = Collections.emptyList();
        this.defaultValues = Collections.emptyList();
    }

    // --- Setters used by generated code via createDirect() ---
    public void setRequired(boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setValueSeparator(char valueSeparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAskIfNotSet(boolean askIfNotSet) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAcceptNameWithoutDashes(boolean acceptNameWithoutDashes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setOverrideRequired(boolean overrideRequired) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setNegatable(boolean negatable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setNegationPrefix(String negationPrefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setInherited(boolean inherited) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setDescriptionUrl(String descriptionUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setIsUrl(boolean isUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setOptionalValue(boolean optionalValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFallbackValue(String fallbackValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getFallbackValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasFallbackValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setDefaultValues(List<String> defaultValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCompleter(OptionCompleter completer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setValidator(OptionValidator validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setActivator(OptionActivator activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setRenderer(OptionRenderer renderer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFieldSetter(BiConsumer<Object, Object> fieldSetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFieldResetter(Consumer<Object> fieldResetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public BiConsumer<Object, Object> getFieldSetter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Consumer<Object> getFieldResetter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFieldGetter(java.util.function.Function<Object, Object> fieldGetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public java.util.function.Function<Object, Object> getFieldGetter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFieldAccessor(FieldAccessor fieldAccessor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FieldAccessor getFieldAccessor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAliases(List<String> aliases) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getAliases() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHelpGroup(String helpGroup) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getHelpGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setExclusiveWith(List<String> exclusiveWith) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getExclusiveWith() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setAllowedValues(List<String> allowedValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getAllowedValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasAllowedValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setVisibility(org.aesh.command.option.OptionVisibility visibility) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public org.aesh.command.option.OptionVisibility getVisibility() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setOrder(int order) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getOrder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setDeclarationOrder(int declarationOrder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getDeclarationOrder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasAlias(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMixinFieldName(String mixinFieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getMixinFieldName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isMixinOption() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Object resolveMixinInstance(Object commandInstance) {
        if (mixinFieldName == null)
            return commandInstance;
        try {
            Field mixinField = getField(commandInstance.getClass(), mixinFieldName);
            if (mixinField == null)
                throw new NoSuchFieldException("Mixin field '" + mixinFieldName + "' not found on " + commandInstance.getClass().getName());
            if (!Modifier.isPublic(mixinField.getModifiers()))
                mixinField.setAccessible(true);
            Object mixinInstance = mixinField.get(commandInstance);
            if (mixinInstance == null) {
                mixinInstance = mixinField.getType().getDeclaredConstructor().newInstance();
                mixinField.set(commandInstance, mixinInstance);
            }
            return mixinInstance;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to resolve mixin field '" + mixinFieldName + "' on " + commandInstance.getClass().getName(), e);
        }
    }

    /**
     * Captures the current field value from the command instance so it can be
     * restored on reset. Call once after the command is fully constructed.
     * Only captures non-null reference-type values.
     */
    public void captureInitialValue(Object instance) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void resetField(Object instance) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Object typeDefault() {
        if (type == null || !type.isPrimitive())
            return null;
        if (type == boolean.class)
            return Boolean.FALSE;
        if (type == int.class)
            return 0;
        if (type == long.class)
            return 0L;
        if (type == short.class)
            return (short) 0;
        if (type == byte.class)
            return (byte) 0;
        if (type == char.class)
            return ' ';
        if (type == float.class)
            return 0.0f;
        if (type == double.class)
            return 0.0d;
        return null;
    }

    @SuppressWarnings("unchecked")
    private void restoreInitialValue(Object instance) {
        if (fieldAccessor != null || fieldSetter != null) {
            Object val = initialValue;
            if (initialValue instanceof Collection) {
                try {
                    val = initialValue.getClass().getDeclaredConstructor().newInstance();
                } catch (ReflectiveOperationException e) {
                    val = new ArrayList<>();
                }
            } else if (initialValue instanceof Map) {
                try {
                    val = initialValue.getClass().getDeclaredConstructor().newInstance();
                } catch (ReflectiveOperationException e) {
                    val = new HashMap<>();
                }
            }
            if (fieldAccessor != null)
                fieldAccessor.set(instance, val);
            else
                fieldSetter.accept(instance, val);
            return;
        }
        try {
            Object target = resolveMixinInstance(instance);
            Field field = getField(target.getClass(), fieldName);
            if (field == null)
                return;
            if (!Modifier.isPublic(field.getModifiers()))
                field.setAccessible(true);
            if (initialValue instanceof Collection) {
                field.set(target, initialValue.getClass().getDeclaredConstructor().newInstance());
            } else if (initialValue instanceof Map) {
                field.set(target, initialValue.getClass().getDeclaredConstructor().newInstance());
            } else {
                field.set(target, initialValue);
            }
        } catch (ReflectiveOperationException e) {
            if (fieldResetter != null) {
                fieldResetter.accept(instance);
            }
        }
    }

    public String shortName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String name() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addValue(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addValues(List<String> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getLastValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isOptionalValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasMultipleValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isRequired() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean doOverrideRequired() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Class<?> type() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String description() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public char getValueSeparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isProperty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getArgument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<String> getDefaultValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addProperty(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Map<String, String> getProperties() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OptionType getOptionType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getFieldName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the display label for this argument in help/synopsis.
     * If paramLabel is set, returns that; otherwise returns the fieldName.
     */
    public String getDisplayLabel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setParamLabel(String paramLabel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getParamLabel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setArity(org.aesh.command.option.Arity arity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public org.aesh.command.option.Arity getArity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setIndex(String index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public org.aesh.command.option.IndexRange getIndexRange() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasIndexRange() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the argument has reached its maximum arity.
     * Returns false if no arity is set (legacy unlimited behavior for @Arguments).
     */
    public boolean isArityFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Converter converter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OptionCompleter completer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OptionValidator validator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OptionActivator activator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isActivated(ParsedCommand parsedCommand) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OptionParser parser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setParser(OptionParser parser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setParent(ProcessedCommand parent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedCommand parent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * might return null if render is not specified
     */
    public OptionRenderer getRenderer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isLongNameUsed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setLongNameUsed(boolean longNameUsed) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setEndsWithSeparator(boolean endsWithSeparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getEndsWithSeparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean askIfNotSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean acceptNameWithoutDashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SelectorType selectorType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isNegatable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getNegationPrefix() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the negated form of this option's name.
     * For example, if name is "verbose" and prefix is "no-", returns "no-verbose".
     * Returns null if this option is not negatable.
     */
    public String getNegatedName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if this option was specified in its negated form (e.g., --no-verbose).
     */
    public boolean isNegatedByUser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets whether this option was specified in its negated form.
     */
    public void setNegatedByUser(boolean negatedByUser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if this option should be inherited by subcommands.
     * Inherited options are automatically available to subcommands when
     * in sub-command mode.
     */
    public boolean isInherited() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the documentation URL for this option's description.
     */
    public String getDescriptionUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if this option's value should be treated as a URL.
     */
    public boolean isUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the option value formatted as a hyperlink when appropriate.
     *
     * @param supportsHyperlinks whether the terminal supports OSC 8 hyperlinks
     * @return the value, optionally wrapped in hyperlink escape sequences
     */
    public String getFormattedValue(boolean supportsHyperlinks) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getDisplayName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TerminalString getRenderedNameWithDashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<TerminalString> getRenderedAliasNamesWithDashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the negated form of the option name with dashes for completion.
     * For example, for option "verbose" with prefix "no-", returns "--no-verbose".
     * Returns null if this option is not negatable.
     */
    public TerminalString getRenderedNegatedNameWithDashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the value placeholder for this option. If an explicit argument
     * label is set, uses that. Otherwise, for options that accept values
     * (not booleans, not optionalValue, not fallbackValue), derives a
     * placeholder from the option name.
     */
    private String getValuePlaceholder() {
        if (argument != null && argument.length() > 0)
            return argument;
        if (optionType == OptionType.BOOLEAN)
            return null;
        if (optionType == OptionType.ARGUMENT || optionType == OptionType.ARGUMENTS)
            return null;
        if (optionType == OptionType.GROUP)
            return "key=value";
        if (type == Boolean.class || type == boolean.class)
            return null;
        if (hasValue() && !isOptionalValue() && !hasFallbackValue()) {
            String label = name != null && !name.isEmpty() ? name : fieldName;
            return label != null && !label.isEmpty() ? label : null;
        }
        return null;
    }

    public int getFormattedLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    //TODO: add offset, offset for descriptionstart and break on width
    public String getFormattedOption(int offset, int descriptionStart, int width) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getFormattedOption(int offset, int descriptionStart, int width, boolean supportsHyperlinks) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getFormattedOption(int offset, int descriptionStart, int width, boolean supportsHyperlinks, String resolvedDescription) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public Object doConvert(String inputValue, InvocationProviders invocationProviders, Object command, AeshContext aeshContext, boolean doValidation) throws OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public void injectValueIntoField(Object instance, InvocationProviders invocationProviders, AeshContext aeshContext, boolean doValidation) throws OptionValidatorException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void setField(Object instance, Object value) {
        if (fieldAccessor != null)
            fieldAccessor.set(instance, value);
        else
            fieldSetter.accept(instance, value);
    }

    private void injectValueWithSetter(Object instance, InvocationProviders invocationProviders, AeshContext aeshContext, boolean doValidation) throws OptionValidatorException {
        if (optionType == OptionType.NORMAL || optionType == OptionType.BOOLEAN || optionType == OptionType.ARGUMENT) {
            if (negatedByUser && optionType == OptionType.BOOLEAN) {
                setField(instance, doConvert("false", invocationProviders, instance, aeshContext, doValidation));
            } else if (getValue() != null)
                setField(instance, doConvert(getValue(), invocationProviders, instance, aeshContext, doValidation));
            else if (defaultValues.size() > 0) {
                setField(instance, doConvert(defaultValues.get(0), invocationProviders, instance, aeshContext, doValidation));
            }
        } else if (optionType == OptionType.LIST || optionType == OptionType.ARGUMENTS) {
            Collection<Object> tmpSet;
            if (Set.class.isAssignableFrom(type))
                tmpSet = new HashSet<>();
            else
                tmpSet = new ArrayList<>();
            if (values.size() > 0) {
                for (String in : values) tmpSet.add(doConvert(in, invocationProviders, instance, aeshContext, doValidation));
            } else if (defaultValues.size() > 0) {
                for (String in : defaultValues) tmpSet.add(doConvert(in, invocationProviders, instance, aeshContext, doValidation));
            }
            setField(instance, tmpSet);
        } else if (optionType == OptionType.GROUP) {
            Map<String, Object> tmpMap = newHashMap();
            for (String propertyKey : properties.keySet()) tmpMap.put(propertyKey, doConvert(properties.get(propertyKey), invocationProviders, instance, aeshContext, doValidation));
            setField(instance, tmpMap);
        }
    }

    private void injectValueWithReflection(Object instance, InvocationProviders invocationProviders, AeshContext aeshContext, boolean doValidation) throws OptionValidatorException {
        try {
            Field field = getField(instance.getClass(), fieldName);
            //for some options, the field might be null. eg generatedHelp
            //if so we ignore it
            if (field == null)
                return;
            if (!Modifier.isPublic(field.getModifiers()))
                field.setAccessible(true);
            if (!Modifier.isPublic(instance.getClass().getModifiers())) {
                Constructor constructor = instance.getClass().getDeclaredConstructor();
                if (constructor != null)
                    constructor.setAccessible(true);
            }
            if (optionType == OptionType.NORMAL || optionType == OptionType.BOOLEAN || optionType == OptionType.ARGUMENT) {
                // Handle negatable options - when used in negated form (e.g., --no-verbose), inject false
                if (negatedByUser && optionType == OptionType.BOOLEAN) {
                    field.set(instance, doConvert("false", invocationProviders, instance, aeshContext, doValidation));
                } else if (getValue() != null)
                    field.set(instance, doConvert(getValue(), invocationProviders, instance, aeshContext, doValidation));
                else if (defaultValues.size() > 0) {
                    field.set(instance, doConvert(defaultValues.get(0), invocationProviders, instance, aeshContext, doValidation));
                }
            } else if (optionType == OptionType.LIST || optionType == OptionType.ARGUMENTS) {
                Collection<Object> tmpSet = initializeCollection(field);
                if (values.size() > 0) {
                    for (String in : values) tmpSet.add(doConvert(in, invocationProviders, instance, aeshContext, doValidation));
                } else if (defaultValues.size() > 0) {
                    for (String in : defaultValues) tmpSet.add(doConvert(in, invocationProviders, instance, aeshContext, doValidation));
                }
                field.set(instance, tmpSet);
            } else if (optionType == OptionType.GROUP) {
                if (field.getType().isInterface() || Modifier.isAbstract(field.getType().getModifiers())) {
                    Map<String, Object> tmpMap = newHashMap();
                    for (String propertyKey : properties.keySet()) tmpMap.put(propertyKey, doConvert(properties.get(propertyKey), invocationProviders, instance, aeshContext, doValidation));
                    field.set(instance, tmpMap);
                } else {
                    Map<String, Object> tmpMap = (Map<String, Object>) field.getType().newInstance();
                    for (String propertyKey : properties.keySet()) tmpMap.put(propertyKey, doConvert(properties.get(propertyKey), invocationProviders, instance, aeshContext, doValidation));
                    field.set(instance, tmpMap);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException("Failed to inject value into field: " + fieldName, e);
        }
    }

    @SuppressWarnings("unchecked")
    private Collection<Object> initializeCollection(Field field) throws IllegalAccessException, InstantiationException {
        if (field.getType().isInterface() || Modifier.isAbstract(field.getType().getModifiers())) {
            if (Set.class.isAssignableFrom(field.getType()))
                return new HashSet<>();
            else if (List.class.isAssignableFrom(field.getType()))
                return new ArrayList<>();
            else
                return null;
        } else
            return (Collection) field.getType().newInstance();
    }

    public void updateInvocationProviders(InvocationProviders invocationProviders) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void updateAnsiMode(boolean ansiMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isAnsiMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <S, T> Map<S, T> newHashMap() {
        return new HashMap<>();
    }

    private Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        if (fieldName == null || fieldName.isEmpty())
            return null;
        if (cachedField != null && cachedFieldClass == clazz)
            return cachedField;
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Field f : c.getDeclaredFields()) {
                if (f.getName().equals(fieldName)) {
                    cachedField = f;
                    cachedFieldClass = clazz;
                    return f;
                }
            }
        }
        return null;
    }

    public Object getFieldValue(Object instance) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setFieldValue(Object instance, Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCursorOption() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCursorOption(boolean cursorOption) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCursorValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCursorValue(boolean cursorValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasDefaultValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isTypeAssignableByResourcesOrFile() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
