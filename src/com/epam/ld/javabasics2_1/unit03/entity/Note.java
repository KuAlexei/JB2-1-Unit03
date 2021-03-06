package com.epam.ld.javabasics2_1.unit03.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Note {

    private long id;
    private LocalDateTime dateTime;
    private String record;

    public Note(long id, LocalDateTime dateTime, String record) {
        this.id = id;
        this.dateTime = dateTime;
        this.record = record;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && dateTime.equals(note.dateTime) && record.equals(note.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, record);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Note{");
        sb.append("id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", record='").append(record).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
