package Domain;

import java.io.Serializable;
import java.util.LinkedList;

public class Project implements Serializable {
    private static final long serialVersionUID = 1;
    private int projectId;
    private String projectTitle;
    private String timelineTitle;
    private LinkedList<Note> timeline;


    public Project(String projectTitle, String timelineTitle, int projectId){
        this.projectTitle = projectTitle;
        this.timelineTitle = timelineTitle;
        this.projectId = projectId;
    }

    public static boolean exportProject(){
        return false;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


    public LinkedList<Note> getTimeline() {
        return timeline;
    }

    public void setTimeline(LinkedList<Note> timeline) {
        this.timeline = timeline;
    }

    public String getTimelineTitle() {
        return timelineTitle;
    }

    public void setTimelineTitle(String timelineTitle) {
        this.timelineTitle = timelineTitle;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
