package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;

public class LoadNoteBook extends ACommand<String, Boolean> {

    public LoadNoteBook(String filename) {
        super(new Request(filename));
    }

    @Override
    public Response<Boolean> execute() {
        try {
            NoteBookProvider.getInstance().getNoteBook().loadFromFile(getRequest());
            return new Response<Boolean>(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<Boolean>(false);
    }

}