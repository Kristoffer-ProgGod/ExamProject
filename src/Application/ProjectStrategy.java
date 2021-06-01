package Application;

import Domain.Project;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectStrategy {

    public int deleteProject();
    public int saveProject(Project project) throws SQLException;
    public ArrayList<Project> getAllProject();

}
