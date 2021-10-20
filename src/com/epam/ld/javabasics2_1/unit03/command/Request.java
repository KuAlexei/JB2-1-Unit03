package com.epam.ld.javabasics2_1.unit03.command;

public class Request<T> {

    private final T request;

    public Request(T request) {
        this.request = request;
    }

    public T getRequest() {
        return this.request;
    }

}
