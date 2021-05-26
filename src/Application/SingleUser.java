package Application;

import Database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingleUser implements DataManipulationStrategy{
    Connection connection;


    public SingleUser(){

    }



    @Override
    public int delete() {
        return 0;
    }

    @Override
    public int add() {
        return 0;
    }

    @Override
    public int save() throws SQLException {
        int i;
        connection = MyDatabase.openConnection();
        assert connection != null;

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into tbl_Project VALUES (?,?)");
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "Test Projekt");
        i = preparedStatement.executeUpdate();
        MyDatabase.closeConnection(connection);
        return i;
    }

    @Override
    public boolean getAllProject() {
        return false;
    }

    @Override
    public boolean getAllNotebank() {
        return false;
    }
}
