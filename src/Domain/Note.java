package Domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = 2000;
    private int noteId;
    private String text;
    private String references;
    private int nextNoteAddress;
    private double xPos, yPos;

    public Note(String text, String references){
        this.text = text;
        this.references = references;
    }


    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getReference() {
        return references;
    }

    public void setReference(String reference) {
        this.references = reference;
    }

    public int getNextNoteAddress() {
        return nextNoteAddress;
    }

    public void setNextNoteAddress(int nextNoteAddress) {
        this.nextNoteAddress = nextNoteAddress;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString(){
        return text +" | Reference: "+ references;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
