package Domain;

import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = 2000;
    private int noteId;
    private String text;
    private String reference;
    private int nextNoteAddress;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getNextNoteAddress() {
        return nextNoteAddress;
    }

    public void setNextNoteAddress(int nextNoteAddress) {
        this.nextNoteAddress = nextNoteAddress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
