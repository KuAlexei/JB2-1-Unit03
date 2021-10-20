package com.epam.ld.javabasics2_1.unit03.command;

public class CommandExecutor {

    public Response exec(ICommand command) {
        return command.execute();
    }

}
