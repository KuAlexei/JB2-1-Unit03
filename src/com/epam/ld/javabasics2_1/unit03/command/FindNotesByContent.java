package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookProvider;
import com.epam.ld.javabasics2_1.unit03.entity.Note;

import java.util.List;
import java.util.stream.Collectors;

public class FindNotesByContent extends ACommand<String, List<Note>>  {

    public FindNotesByContent(String content) {
        super(new Request(content));
    }

    @Override
    public Response<List<Note>> execute() {
        return new Response<>(NoteBookProvider.getInstance().getNoteBook().getAllNotes().stream().filter(n -> n.getRecord().contains(getRequest())).collect(Collectors.toList()));
    }
}
