package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookConsoleView;
import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;
import java.util.List;

public class ShowAllNotes extends ACommand<Void, Void> {

    @Override
    public Response<Void> execute() {
        NoteBookConsoleView notePrinter = new NoteBookConsoleView();
        notePrinter.print(NoteBookProvider.getInstance().getNoteBook());
        return new Response(null);
    }

}
