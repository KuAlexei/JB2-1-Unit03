package com.epam.ld.javabasics2_1.unit03.command;

import com.epam.ld.javabasics2_1.unit03.NoteBookConsoleView;
import com.epam.ld.javabasics2_1.unit03.entity.Note;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    @Test
    void exec() {
        CommandExecutor ce = new CommandExecutor();
        NoteBookConsoleView notePrinter = new NoteBookConsoleView();
        List<Note> notes;

        assertDoesNotThrow(() -> ce.exec(new LoadNoteBook("test_resources/OK.csv")));
        assertDoesNotThrow(() -> ce.exec(new ShowAllNotes()));
        notes = (List<Note>) ce.exec(new FindNotesByContent("ipsum")).getResponse();
        notePrinter.print(notes);
        assertTrue(notes.size() == 1);
        notes = (List<Note>) ce.exec(new FindNotesByDate(LocalDate.of(2000,1,1))).getResponse();
        notePrinter.print(notes);
        assertTrue(notes.size() == 1);
        assertDoesNotThrow(() -> ce.exec(new CreateNoteBook()));
        assertDoesNotThrow(() -> ce.exec(new AddNote("test Message")));
        assertDoesNotThrow(() -> ce.exec(new SaveNoteBook("test_resources/commandSave.csv")));

    }
}