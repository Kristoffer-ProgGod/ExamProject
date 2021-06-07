package Domain;

import Database.MyDatabase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Notebank implements Serializable {
    private static final long serialVersionUID = 3000;
    private int notebankId;
    private String notebankTitle;
    private LinkedList<Note> notebankLinkedList;

    public Notebank(String notebankTitle) {
        this.notebankTitle = notebankTitle;
        this.notebankLinkedList = new LinkedList<>();
    }

    public static boolean exportNotebank(){
        return false;
    }


    public int getNotebankId() {
        return notebankId;
    }

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

    @Override
    public String toString(){
        return ""+this.notebankTitle;
    }
}
