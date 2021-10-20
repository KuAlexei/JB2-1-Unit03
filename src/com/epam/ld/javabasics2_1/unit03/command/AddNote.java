package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;
import java.time.LocalDateTime;

public class AddNote extends ACommand<Object[], Boolean> {

    public AddNote(String note) {
        this(null, note);
    }

    public AddNote(LocalDateTime date, String note) {
        super(new Request<>(new Object[]{(date==null?LocalDateTime.now():date), note}));
    }

    @Override
    public Response<Boolean> execute() {
        Object[] req = getRequest();
        NoteBookProvider.getInstance().getNoteBook().addNote((LocalDateTime) req[0], (String) req[1]);
        return new Response<Boolean>(true);
    }

}
