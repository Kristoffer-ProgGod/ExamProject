package Application;

import Domain.Notebank;

import java.sql.SQLException;

public interface NotebankStrategy {

    public int deleteNotebank();
    public int saveNotebank(Notebank notebank) throws SQLException;
    public boolean getAllNotebank();

}
