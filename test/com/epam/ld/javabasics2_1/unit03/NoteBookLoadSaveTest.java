package com.epam.ld.javabasics2_1.unit03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NoteBookLoadSaveTest {
    @BeforeAll
    static void beforeAll() {
        NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
        noteBook.addNote("Single line note!");
        noteBook.addNote("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        noteBook.addNote(LocalDateTime.of(2000,1,1,0,0,59),"Happy 2000 YEAR!");
    }

    @Test
    void saveToFile() {
        assertDoesNotThrow(() -> NoteBookProvider.getInstance().getNoteBook().saveToFile("test_resources/saved.csv"));
    }

    @Test
    void loadFromFile() {
        assertDoesNotThrow(() -> NoteBookProvider.getInstance().getNoteBook().loadFromFile("test_resources/OK.csv"));
        assertThrows(FileNotFoundException.class, () -> NoteBookProvider.getInstance().getNoteBook().loadFromFile("test_resources/fileNotFound.csv"));
        assertThrows(IllegalArgumentException.class, () -> NoteBookProvider.getInstance().getNoteBook().loadFromFile("test_resources/wrongHeader.csv"));
        assertThrows(Exception.class, () -> NoteBookProvider.getInstance().getNoteBook().loadFromFile("test_resources/sameIDs.csv"));
        assertThrows(NumberFormatException.class, () -> NoteBookProvider.getInstance().getNoteBook().loadFromFile("test_resources/wrongIDtype.csv"));
    }

}