package Domain;

import java.util.LinkedList;

public class Project {
    private int projectId;
    private String projectTitle;
    private String timelineTitle;
    private LinkedList<Note> timeline;

    public static boolean exportProject(){
        return false;
    }
}
