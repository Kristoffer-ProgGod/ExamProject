package Domain;

import Database.MyDatabase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Notebank implements Serializable {
    /*This attribute is to ensure when serializing this object, the program knows what kind of object it is reading
    when deserializing the project.
    */
    private static final long serialVersionUID = 3000;
    private int notebankId;
    private String notebankTitle;
    private LinkedList<Note> notebankLinkedList;

    //The default constructor for our Notebank objects. ID is set seperately so it can be calculated from values in the databse.
    //The list is always a new list, so that anything is never re-used.
    public Notebank(String notebankTitle) {
        this.notebankTitle = notebankTitle;
        this.notebankLinkedList = new LinkedList<>();
    }

    public int getNotebankId() {
        return notebankId;
    }

    /*This sets the ID of our notebank. It retrieves the maximum ID value of current notebanks in the database
    and ensures that the generated ID is a greater value. This of course is only relevant in multiuser scenarios.
    */
    public void setNotebankId() {
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            Connection connection = MyDatabase.openConnection();
            preparedStatement = connection.prepareStatement("SELECT MAX(fldNotebankId) FROM tbl_Notebank");


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

        this.notebankId = id;
    }
    //The last of our generic getters and setters for Notebank object.
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

    //The toString() of our Notebank. Formatted to fit purposes of only showing the title.
    @Override
    public String toString(){
        return ""+this.notebankTitle;
    }
}
