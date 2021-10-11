package com.epam.ld.javabasics2_1.unit03;

public class NoteBookProvider {

    private static NoteBookProvider instance;
    private NoteBook noteBook = new NoteBook();

    private NoteBookProvider() {
    }

    public static NoteBookProvider getInstance() {
        if (instance == null) {
            instance = new NoteBookProvider();
        }
        return instance;
    }

    public NoteBook getNoteBook() {
        return this.noteBook;
    }

}
