package Domain;

import Database.MyDatabase;

import java.io.*;
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
    private LinkedList<Note> timeline;


    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
        this.timeline = new LinkedList<Note>();
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

    public int getProjectId() {
        return projectId;
    }

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
