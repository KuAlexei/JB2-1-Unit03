package com.epam.ld.javabasics2_1.unit03;

import com.epam.ld.javabasics2_1.unit03.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NoteBook {

    private long nextNoteId = 1;
    private List<Note> notes = new ArrayList<Note>();

    public Note getNoteById(long id) {
        return notes.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    public List<Note> getNotesForDay(LocalDate date) {
        return (List<Note>) notes.stream().filter(n -> n.getDateTime().toLocalDate().isEqual(date)).collect(Collectors.toList());
    }

    public List<Note> getAllNotes() {
        return Collections.unmodifiableList(notes);
    }

    public Note addNote(String record) {
        return addNote(null, record);
    }

    public Note addNote(LocalDateTime dateTime, String record) {
        if (record == null) return null;
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        Note aNewNote = new Note(nextNoteId++, dateTime, record);
        notes.add(aNewNote);
        return aNewNote;
    }

    public boolean deleteNote(Note note) {
        return notes.remove(note);
    }

    public boolean deleteNote(long id) {
        Note noteToDelete = getNoteById(id);
        return deleteNote(noteToDelete);
    }

}
