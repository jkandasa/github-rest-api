package org.jkandasa.restclient.core;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jkandasa.restclient.core.jaxrs.RestFactory;
import org.jkandasa.restclient.core.typeresolvers.CollectionJavaTypeResolver;
import org.jkandasa.restclient.core.typeresolvers.SimpleJavaTypeResolver;

public abstract class BaseClient<T> {

    private T restAPI;
    private SimpleJavaTypeResolver simpleJavaTypeResolver = new SimpleJavaTypeResolver();
    private CollectionJavaTypeResolver collectionJavaTypeResolver = new CollectionJavaTypeResolver();

    public BaseClient(ClientInfo clientInfo, RestFactory<T> restFactory) {
        checkNotNull(clientInfo);
        restAPI = restFactory.createAPI(clientInfo);
    }

    public T restApi() {
        return this.restAPI;
    }

    public SimpleJavaTypeResolver simpleResolver() {
        return simpleJavaTypeResolver;
    }

    public CollectionJavaTypeResolver collectionResolver() {
        return collectionJavaTypeResolver;
    }

}