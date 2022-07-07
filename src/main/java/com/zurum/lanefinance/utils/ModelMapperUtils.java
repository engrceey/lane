package com.zurum.lanefinance.utils;

import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ModelMapperUtils {

    private static final ModelMapper modelMapper;

    static {
        Condition<?, ?> isStringBlank = new AbstractCondition<>() {
            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                if (context.getSource() instanceof String) {
                    return null != context.getSource() && !"".equals(context.getSource());
                } else {
                    return context.getSource() != null;
                }
            }
        };

        modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setPropertyCondition(isStringBlank)
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtils() {
    }

    public static <S, D> D map(final S entity, Class<D> outClazz) {
        return modelMapper.map(entity, outClazz);
    }

    public static <S, D> List<D> mapAll(final Collection<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }

    public static <S, D> List<D> mapAll(final List<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }

    public static <S, D> List<D> mapAll(final Page<S> entityList, Class<D> outClazz) {
        return entityList.stream().map(entity -> map(entity, outClazz)).collect(Collectors.toList());
    }


    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}