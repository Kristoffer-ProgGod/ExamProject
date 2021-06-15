package Test;

import Application.SingleUser;
import Domain.Project;

import java.sql.SQLException;
import java.io.*;

class SingleUserTest {
    private Project testProject;
    private SingleUser singleUser;


    /*
    Initializes the objects need for the tests in the class
    and is automatically called before each test runs.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testProject = new Project("TestingProject");
        singleUser = new SingleUser();
    }


    /*
    Deletes the TestingProject file after each test is done
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() throws Exception {
        File file = new File("src\\Foundation\\ProjectSaveFiles\\"+testProject.getProjectTitle()+".ser");
        boolean deleted = file.delete();
        if(!deleted){
            throw new Exception("File not deleted");
        }
        testProject = null;
        singleUser = null;
    }

    //Tests the createProject method
    @org.junit.jupiter.api.Test
    void createProject() throws SQLException {
        singleUser.createProject(testProject);
    }

    //Tests the saveProject method
    @org.junit.jupiter.api.Test
    void saveProject() {
        singleUser.saveProject(testProject);
    }
}