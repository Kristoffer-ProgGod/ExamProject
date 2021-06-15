package Application;

import Domain.Project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProjectStrategy {

    int deleteProject() throws SQLException;
    int saveProject(Project project) throws SQLException;
    int createProject(Project currentProject) throws SQLException;
    ArrayList<Project> getAllProject() throws SQLException;
    void editProjectTitle(String projectName) throws IOException;

}
