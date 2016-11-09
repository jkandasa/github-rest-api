package org.jkandasa.restclient.core;

public interface ClientResponse<T> {

    int getStatusCode();

    void setStatusCode(int statusCode);

    boolean isSuccess();

    void setSuccess(boolean success);

    T getEntity();

    void setEntity(T entity);

    String getErrorMsg();

    void setErrorMsg(String errorMsg);

}