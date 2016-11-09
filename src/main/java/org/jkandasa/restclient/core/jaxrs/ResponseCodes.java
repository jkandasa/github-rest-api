package org.jkandasa.restclient.core.jaxrs;

public enum ResponseCodes {

    GET_SUCCESS_200(200),
    CREATE_SUCCESS_201(201),
    DELETE_SUCCESS_200(200),
    NO_CONTENT_204(204);

    private int code;

    ResponseCodes(int code) {
        this.code = code;
    }

    public int value() {
        return this.code;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}