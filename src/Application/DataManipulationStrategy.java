package Application;

import java.sql.SQLException;

public interface DataManipulationStrategy {

    public int deleteProject();
    public int deleteNotebank();
    public int saveProject() throws SQLException;
    public int saveNotebank() throws SQLException;
    public boolean getAllProject();
    public boolean getAllNotebank();


}
