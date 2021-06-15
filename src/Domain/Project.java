package Domain;

import Database.MyDatabase;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Project implements Serializable {
    /*This attribute is to ensure when serializing this object, the program knows what kind of object it is reading
    when deserializing the project.
    */
    private static final long serialVersionUID = 1000;
    private int projectId;
    private String projectTitle;
    private LinkedList<Note> timeline;

    //The default constructor for our Project object. Very similar to Notebank, just storing the Notes in a timeline.
    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
        timeline = new LinkedList<Note>();
    }

    //Generic getters and setters for the different attributes of our Project object.
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

    public int getProjectId() {
        return projectId;
    }

    //Setting our ProjectID similar to how we did it in Notebank, we generate the ID from the current max value from the Database.
    public void setProjectId() {
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            Connection connection = MyDatabase.openConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(fldProjectId) FROM tbl_Project");


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                id = 1;
                MyDatabase.closeConnection(connection);
            }

            if (resultSet.next()) {
                //id is set equal to the current max id plus one.
                id = resultSet.getInt(1) + 1;
                MyDatabase.closeConnection(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.projectId = id;
    }
}
