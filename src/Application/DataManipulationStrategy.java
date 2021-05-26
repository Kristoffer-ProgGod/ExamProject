package Application;

import java.sql.SQLException;

public interface DataManipulationStrategy {

    public int delete();
    public int add();
    public int save() throws SQLException;
    public boolean getAllProject();
    public boolean getAllNotebank();


}
