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
    public int deleteProject() {
        return 0;
    }

    @Override
    public int deleteNotebank() {
        return 0;
    }

    @Override
    public int saveProject() throws SQLException {
        return 0;
    }

    @Override
    public int saveNotebank() throws SQLException {
        return 0;
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
