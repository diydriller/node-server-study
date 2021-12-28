package com.spring.redis.config;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
