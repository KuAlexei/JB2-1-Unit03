package com.epam.ld.javabasics2_1.unit03.command;

public class Response<T> {

    private final T response;

    public Response(T response) {
        this.response = response;
    }

    public T getResponse() {
        return this.response;
    }
}
