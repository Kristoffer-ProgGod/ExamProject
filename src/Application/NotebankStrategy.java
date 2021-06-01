package Application;

import Domain.Notebank;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotebankStrategy {

    public int deleteNotebank();
    public int saveNotebank(Notebank notebank) throws SQLException;
    public ArrayList<Notebank> getAllNotebank();

}
