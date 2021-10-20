package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBook;
import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;

public class CreateNoteBook extends ACommand<Void, Boolean> {

    @Override
    public Response<Boolean> execute() {
        // Not actual creating a new one, but just cleaning up  the existing notebook.
        NoteBookProvider.getInstance().getNoteBook().deleteAllNotes();
        return new Response<>(true);
    }

}
