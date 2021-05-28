package Application;

import Domain.Notebank;
import Domain.Project;

import java.sql.SQLException;

public class MultiUser implements ProjectStrategy, NotebankStrategy {
    @Override
    public int deleteProject() {
        return 0;
    }

    @Override
    public int deleteNotebank() {
        return 0;
    }

    @Override
    public int saveProject(Project project) throws SQLException {
        return 0;
    }

    @Override
    public int saveNotebank(Notebank notebank) throws SQLException {
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
