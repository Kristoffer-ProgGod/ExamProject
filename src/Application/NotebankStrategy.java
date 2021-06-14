package Application;

import Domain.Notebank;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface NotebankStrategy {

    public int deleteNotebank() throws SQLException;
    public int saveNotebank(Notebank notebank) throws SQLException;
    public int createNotebank(Notebank notebank) throws SQLException;
    public ArrayList<Notebank> getAllNotebank();
    public void editNotebankTitle(String notebankName) throws IOException;

}
