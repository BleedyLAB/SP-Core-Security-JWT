package ru.studentsplatform.security.model;

public class Note {

    private Long id;

    public Note(Long id, String textNote) {
        this.id = id;
        this.textNote = textNote;
    }

    private String textNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", textNote='" + textNote + '\'' +
                '}';
    }

}
