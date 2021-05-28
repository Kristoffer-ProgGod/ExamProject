package Domain;

import java.io.Serializable;
import java.util.LinkedList;

public class Notebank implements Serializable {
    private int notebankId;
    private String notebankTitle;
    private LinkedList<Note> notebankLinkedList;

    public static boolean exportNotebank(){
        return false;
    }

}
