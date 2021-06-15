package Application;

import Domain.Notebank;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface NotebankStrategy {

    int deleteNotebank() throws SQLException;
    int saveNotebank(Notebank notebank) throws SQLException;
    int createNotebank(Notebank notebank) throws SQLException;
    ArrayList<Notebank> getAllNotebank();
    void editNotebankTitle(String notebankName) throws IOException;

}
