package com.epam.ld.javabasics2_1.unit03.command;

public abstract class ACommand<Req, Res> implements ICommand<Req, Res> {

    private final Req request;

    public ACommand() {
        this(new Request<Req>(null));
    }

    public ACommand(Request<Req> request) {
        this.request = request.getRequest();
    }

    public Req getRequest() {
        return this.request;
    }

    public abstract Response<Res> execute();

}
