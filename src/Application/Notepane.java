package Application;

import Domain.Note;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Notepane extends Pane implements Serializable {

    //The instance of note associated with the notepane
    Note note;

    //Getters and setters for note associated with the current notepane
    public Note getNote() {
        return note;
    }
    public void setNote(Note note){
        this.note = note;
    }
}
