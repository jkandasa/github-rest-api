package org.jkandasa.restclient.core.typeresolvers;

import java.util.List;

import org.jkandasa.restclient.core.jaxrs.fasterxml.jackson.ClientObjectMapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectionJavaTypeResolver {

    private final ObjectMapper objectMapper;

    public CollectionJavaTypeResolver() {
        this.objectMapper = new ClientObjectMapper();
    }

    public JavaType get(@SuppressWarnings("rawtypes") Class<? extends List> collectionClazz, Class<?> clazz) {
        JavaType clazzType = objectMapper.getTypeFactory().constructType(clazz);
        return objectMapper.getTypeFactory().constructCollectionType(collectionClazz, clazzType);
    }

    public JavaType get(@SuppressWarnings("rawtypes") Class<? extends List> collectionClazz, Class<?> clazz,
            Class<?> parametrizedClazz) {
        JavaType parametrizedClazzType = objectMapper.getTypeFactory().constructType(parametrizedClazz);
        JavaType clazzType = objectMapper.getTypeFactory().constructParametrizedType(clazz, clazz,
                parametrizedClazzType);

        return objectMapper.getTypeFactory().constructCollectionType(collectionClazz, clazzType);
    }
}