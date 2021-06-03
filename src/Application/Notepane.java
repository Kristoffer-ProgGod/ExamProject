package Application;

import Domain.Note;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Notepane extends Pane implements Serializable {

    Note note;

    public Note getNote() {
        return note;
    }
    public void setNote(Note note){
        this.note = note;
    }
}
