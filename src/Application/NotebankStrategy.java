package Application;

import java.sql.SQLException;

public interface NotebankStrategy {

    public int deleteNotebank();
    public int saveNotebank() throws SQLException;
    public boolean getAllNotebank();

}
