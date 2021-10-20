package com.epam.ld.javabasics2_1.unit03;

import com.epam.ld.javabasics2_1.unit03.entity.Note;
import org.apache.commons.csv.CSVFormat; //org.apache.commons:commons-csv:1.8
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class NoteBook {

    private static final CSVFormat FILE_FORMAT = CSVFormat.RFC4180
            .withHeader("ID", "DateTime", "Note")
            .withIgnoreHeaderCase()
            .withAllowDuplicateHeaderNames(false);
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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

    public void deleteAllNotes() {
        notes.clear();
        nextNoteId = 1; //resetting id
    }

    public void saveToFile(String filename) throws Exception {
        try(Writer file = new FileWriter(filename, false)) {
            CSVPrinter printer = FILE_FORMAT.print(file);
            for (Note note : notes) {
                printer.printRecord(note.getId(), note.getDateTime().format(DATETIME_FORMAT), note.getRecord());
            }
        }
    }

    public void loadFromFile(String filename) throws Exception {
        long id = 0;
        Map<Long, Note> newNotes = new HashMap<>();
        try(Reader file = new FileReader(filename)) {
            Iterable<CSVRecord> records = FILE_FORMAT.withFirstRecordAsHeader().parse(file);
            for (CSVRecord record : records) {
                Long nodeId = Long.parseLong(record.get("ID"));
                if (newNotes.containsKey(nodeId)) {
                    throw new Exception("File " + filename + " contains at least 2 records with same id (" + nodeId + ")");
                }
                id = Long.max(id, nodeId);
                LocalDateTime dataTime = DATETIME_FORMAT.parse(record.get("DateTime"), LocalDateTime::from);
                String note = record.get("Note");
                newNotes.put(nodeId, new Note(nodeId, dataTime, note));
            }
        }
        notes.clear();
        notes.addAll(newNotes.values());
        nextNoteId = id++;
    }

}
