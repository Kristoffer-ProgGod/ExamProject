package Domain;

import Database.MyDatabase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Project implements Serializable {
    private static final long serialVersionUID = 1000;
    private int projectId;
    private String projectTitle;
    private String timelineTitle;
    private LinkedList<Note> timeline;


    public Project(String projectTitle, String timelineTitle, int projectId){
        this.projectTitle = projectTitle;
        this.timelineTitle = timelineTitle;
        this.projectId = projectId;
        timeline = new LinkedList<Note>();
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

    public void setProjectId() throws SQLException {
        Connection connection = MyDatabase.openConnection();
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT MAX(fldProjectId) FROM tbl_Project");


            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet==null){
                id = 1;
            }

            if (resultSet.next()) {
                //id is set equal to the current max id plus one.
                id = resultSet.getInt(1)+1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        MyDatabase.closeConnection(connection);

        projectId = id;
    }
}
