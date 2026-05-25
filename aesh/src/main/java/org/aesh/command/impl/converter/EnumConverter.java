package org.aesh.command.impl.converter;

import org.aesh.command.converter.Converter;
import org.aesh.command.converter.ConverterInvocation;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EnumConverter implements Converter<Enum, ConverterInvocation> {

    private final Class<? extends Enum> enumType;

    public EnumConverter(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Enum convert(ConverterInvocation input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
