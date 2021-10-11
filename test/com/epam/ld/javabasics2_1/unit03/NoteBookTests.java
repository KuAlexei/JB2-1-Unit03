package com.epam.ld.javabasics2_1.unit03;

import com.epam.ld.javabasics2_1.unit03.entity.Note;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteBookTests {
    @BeforeAll
    static void beforeAll() {
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        noteBook.addNote("Single line note!");
        noteBook.addNote("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        noteBook.addNote(LocalDateTime.of(2000,1,1,0,0,59),"Happy 2000 YEAR!");
    }

    @Test
    void Note() {
        Note n11 = new Note(1,LocalDateTime.of(2000,2,2,2,2,2)," test");
        Note n12 = new Note(1,LocalDateTime.of(2000,2,2,2,2,2)," test");
        Note n13 = new Note(1,LocalDateTime.of(2000,2,2,2,2,2),"test");
        Note n21 = new Note(1,LocalDateTime.now(),"test");
        assertEquals(n11, n12);
        assertNotEquals(n11, n13);
        assertNotEquals(n21, n13);
    }

    @Test
    void NoteBook() {
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        long size = noteBook.getAllNotes().size();
        Note toDelete = noteBook.addNote("Test Note to DELETE");
        assertEquals(size + 1, noteBook.getAllNotes().size());
        assertEquals(toDelete, noteBook.getNoteById(toDelete.getId()));
        assertTrue(noteBook.deleteNote(toDelete.getId()));
        assertEquals(size, noteBook.getAllNotes().size());
        assertNull(noteBook.getNoteById(toDelete.getId()));
    }

    @Test
    void NoteBookConsoleView() {
        NoteBookConsoleView nbcv = new NoteBookConsoleView();
        assertAll("NoteBookConsoleView",
                () -> assertDoesNotThrow(() -> nbcv.print(NoteBookProvider.getInstance().getNoteBook()), "Existing Notebook"),
                () -> assertDoesNotThrow(() -> nbcv.print(new NoteBook()), "Empty Notebook"),
                () -> assertDoesNotThrow(() -> nbcv.print((NoteBook) null), "Notebook is null"),
                () -> assertDoesNotThrow(() -> nbcv.print(NoteBookProvider.getInstance().getNoteBook().getNotesForDay(LocalDate.now())), "Existing List of Notes"),
                () -> assertDoesNotThrow(() -> nbcv.print(new ArrayList<Note>()), "List of Notes is empty"),
                () -> assertDoesNotThrow(() -> nbcv.print((List<Note>) null), "List of Notes is null"),
                () -> assertDoesNotThrow(() -> nbcv.print(NoteBookProvider.getInstance().getNoteBook().getNotesForDay(LocalDate.of(2000,1,1)).stream().findFirst().get()), "Existing Note"),
                () -> assertDoesNotThrow(() -> nbcv.print((Note) null), "Note is null")
        );
    }

}