package Application;

import Domain.Project;

import java.sql.SQLException;

public interface ProjectStrategy {

    public int deleteProject();
    public int saveProject(Project project) throws SQLException;
    public boolean getAllProject();

}
