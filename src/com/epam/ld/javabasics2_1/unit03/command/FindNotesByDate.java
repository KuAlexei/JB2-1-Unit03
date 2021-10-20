package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;
import com.epam.ld.javabasics2_1.unit03.entity.Note;

import java.time.LocalDate;
import java.util.List;

public class FindNotesByDate extends ACommand<LocalDate, List<Note>> {

    public FindNotesByDate(LocalDate date) {
        super(new Request(date));
    }

    @Override
    public Response<List<Note>> execute() {
        return new Response<>(NoteBookProvider.getInstance().getNoteBook().getNotesForDay(getRequest()));
    }

}
