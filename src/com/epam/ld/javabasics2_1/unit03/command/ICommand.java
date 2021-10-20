package com.epam.ld.javabasics2_1.unit03.command;

public interface ICommand<Req, Res> {
    public Response<Res> execute();
}
