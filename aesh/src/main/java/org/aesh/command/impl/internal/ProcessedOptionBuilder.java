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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import org.aesh.command.activator.OptionActivator;
import org.aesh.command.completer.OptionCompleter;
import org.aesh.command.converter.Converter;
import org.aesh.command.impl.completer.BooleanOptionCompleter;
import org.aesh.command.impl.completer.DefaultValueOptionCompleter;
import org.aesh.command.impl.completer.NullOptionCompleter;
import org.aesh.command.impl.converter.NullConverter;
import org.aesh.command.impl.renderer.NullOptionRenderer;
import org.aesh.command.impl.validator.NullValidator;
import org.aesh.command.parser.OptionParser;
import org.aesh.command.parser.OptionParserException;
import org.aesh.command.renderer.OptionRenderer;
import org.aesh.command.validator.OptionValidator;
import org.aesh.converter.CLConverterManager;
import org.aesh.selector.SelectorType;
import org.aesh.util.ReflectionUtil;

/**
 * Build a {@link ProcessedOption} object using the Builder pattern.
 *
 * @author Aesh team
 */
public class ProcessedOptionBuilder {

    private char shortName;

    private String name;

    private String description = "";

    private String argument;

    private Class<?> type;

    private boolean hasValue = true;

    private boolean required = false;

    private boolean isProperty = false;

    private boolean hasMultipleValues = false;

    private char valueSeparator = ' ';

    private OptionType optionType;

    private Converter converter;

    private String fieldName;

    private String paramLabel;

    private String arity;

    private String index;

    private OptionCompleter completer;

    private List<String> defaultValues;

    private OptionValidator validator;

    private OptionActivator activator;

    private OptionRenderer renderer;

    private boolean overrideRequired;

    private OptionParser parser;

    private boolean askIfNotSet = false;

    private boolean acceptNameWithoutDashes = false;

    private SelectorType selectorType;

    private boolean optionalValue = false;

    private String fallbackValue;

    private boolean negatable = false;

    private String negationPrefix = "no-";

    private boolean inherited = false;

    private String descriptionUrl;

    private boolean isUrl = false;

    private BiConsumer<Object, Object> fieldSetter;

    private java.util.function.Consumer<Object> fieldResetter;

    private java.util.function.Function<Object, Object> fieldGetter;

    private FieldAccessor fieldAccessor;

    private String mixinFieldName;

    private List<String> aliases;

    private String helpGroup = "";

    private List<String> exclusiveWith;

    private List<String> allowedValues = java.util.Collections.emptyList();

    private org.aesh.command.option.OptionVisibility visibility = org.aesh.command.option.OptionVisibility.BRIEF;

    private int order = Integer.MAX_VALUE;

    private ProcessedOptionBuilder() {
        defaultValues = java.util.Collections.emptyList();
    }

    public static ProcessedOptionBuilder builder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder shortName(char n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder name(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder askIfNotSet(boolean askIfNotSet) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder description(String description) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder argument(String argument) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder type(Class<?> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder required(boolean required) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder acceptNameWithoutDashes(boolean acceptNameWithoutDashes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder selector(SelectorType selectorType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fieldName(String fieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder paramLabel(String paramLabel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder arity(String arity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder index(String index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder hasValue(boolean hasValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder isProperty(boolean isProperty) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder hasMultipleValues(boolean multipleValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder addDefaultValue(String defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder addAllDefaultValues(List<String> defaultValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder addAllDefaultValues(String[] defaultValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder valueSeparator(char valueSeparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder optionType(OptionType optionType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder converter(Converter converter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder converter(Class<? extends Converter> converter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Converter initConverter(Class<? extends Converter> converterClass) {
        if (converterClass != null && !converterClass.equals(NullConverter.class)) {
            Converter converter = CLConverterManager.getInstance().getConverter(converterClass);
            return converter != null ? converter : ReflectionUtil.newInstance(converterClass);
        } else
            return CLConverterManager.getInstance().getConverter(type);
    }

    public ProcessedOptionBuilder completer(OptionCompleter completer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder completer(Class<? extends OptionCompleter> completer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OptionCompleter initCompleter(Class<? extends OptionCompleter> completerClass) {
        if (completerClass != null && !completerClass.equals(NullOptionCompleter.class)) {
            return ReflectionUtil.newInstance(completerClass);
        } else {
            // File/Resource completers are deferred — ProcessedOption.completer() lazy-creates them
            if (type == Boolean.class || type == boolean.class)
                return new BooleanOptionCompleter();
            else
                return null;
        }
    }

    public ProcessedOptionBuilder validator(OptionValidator validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder validator(Class<? extends OptionValidator> validator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OptionValidator initValidator(Class<? extends OptionValidator> validator) {
        if (validator != null && validator != NullValidator.class)
            return ReflectionUtil.newInstance(validator);
        else
            return null;
    }

    public ProcessedOptionBuilder activator(OptionActivator activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder activator(Class<? extends OptionActivator> activator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OptionActivator initActivator(Class<? extends OptionActivator> activator) {
        if (activator != null && activator != org.aesh.command.impl.activator.NullActivator.class)
            return ReflectionUtil.newInstance(activator);
        else
            return null;
    }

    public ProcessedOptionBuilder renderer(OptionRenderer renderer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder renderer(Class<? extends OptionRenderer> renderer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OptionRenderer initRenderer(Class<? extends OptionRenderer> renderer) {
        if (renderer != null && renderer != NullOptionRenderer.class)
            return ReflectionUtil.newInstance(renderer);
        else
            return null;
    }

    public ProcessedOptionBuilder overrideRequired(boolean overrideRequired) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder parser(OptionParser parser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder parser(Class<? extends OptionParser> parser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OptionParser initParser(Class<? extends OptionParser> parser) {
        if (parser != null)
            return ReflectionUtil.newInstance(parser);
        else
            return null;
    }

    /**
     * Set whether this option accepts an optional value (arity 0..1).
     * When used without a value, the defaultValue is applied.
     */
    public ProcessedOptionBuilder optionalValue(boolean optionalValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fallbackValue(String fallbackValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder negatable(boolean negatable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder negationPrefix(String negationPrefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder inherited(boolean inherited) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder descriptionUrl(String descriptionUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder url(boolean isUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fieldSetter(BiConsumer<Object, Object> fieldSetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fieldResetter(java.util.function.Consumer<Object> fieldResetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fieldGetter(java.util.function.Function<Object, Object> fieldGetter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder fieldAccessor(FieldAccessor fieldAccessor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder mixinFieldName(String mixinFieldName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder aliases(List<String> aliases) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder aliases(String... aliases) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder helpGroup(String helpGroup) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder exclusiveWith(String... exclusiveWith) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder addAllowedValue(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder addAllAllowedValues(String[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder visibility(org.aesh.command.option.OptionVisibility visibility) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOptionBuilder order(int order) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProcessedOption build() throws OptionParserException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
