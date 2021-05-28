package Domain;

import java.io.Serializable;
import java.util.LinkedList;

public class Notebank implements Serializable {
    private static final long serialVersionUID = 3000;
    private int notebankId;
    private String notebankTitle;
    private LinkedList<Note> notebankLinkedList;

    public Notebank(int notebankId, String notebankTitle, LinkedList<Note> notebankLinkedList) {
        this.notebankId = notebankId;
        this.notebankTitle = notebankTitle;
        this.notebankLinkedList = notebankLinkedList;
    }

    public static boolean exportNotebank(){
        return false;
    }


    public int getNotebankId() {
        return notebankId;
    }

    public void setNotebankId(int notebankId) {
        this.notebankId = notebankId;
    }

    public String getNotebankTitle() {
        return notebankTitle;
    }

    public void setNotebankTitle(String notebankTitle) {
        this.notebankTitle = notebankTitle;
    }

    public LinkedList<Note> getNotebankLinkedList() {
        return notebankLinkedList;
    }

    public void setNotebankLinkedList(LinkedList<Note> notebankLinkedList) {
        this.notebankLinkedList = notebankLinkedList;
    }
}
