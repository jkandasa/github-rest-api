package org.jkandasa.restclient.core.typeresolvers;

import org.jkandasa.restclient.core.jaxrs.fasterxml.jackson.ClientObjectMapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleJavaTypeResolver {

    private final ObjectMapper objectMapper;

    public SimpleJavaTypeResolver() {
        this.objectMapper = new ClientObjectMapper();
    }

    public JavaType get(Class<?> clazz) {
        return objectMapper.getTypeFactory().constructType(clazz);
    }

    public JavaType get(Class<?> clazz, Class<?> parametrizedClazz) {
        JavaType parametrizedClazzType = objectMapper.getTypeFactory().constructType(parametrizedClazz);

        return objectMapper.getTypeFactory().constructParametrizedType(clazz, clazz, parametrizedClazzType);
    }
}