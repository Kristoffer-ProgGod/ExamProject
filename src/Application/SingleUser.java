package Application;


import Database.MyDatabase;
import Domain.Notebank;
import Domain.Project;
import javafx.event.ActionEvent;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingleUser implements ProjectStrategy, NotebankStrategy {

    public SingleUser() {

    }

    @Override
    public int deleteProject() {
            HomepageController.projects.remove(SingletonMediator.getInstance().getCurrentProject());
            String projectTitle = SingletonMediator.getInstance().getCurrentProject().getProjectTitle();
            File file = new File("src\\ProjectSaveFiles\\" + projectTitle + ".ser");

            if(file.delete()){
                return 1;
            }else {
                return 0;
            }


    }

    @Override
    public int deleteNotebank() {
        HomepageController.notebanks.remove(SingletonMediator.getInstance().getCurrentNotebank());
        String notebankTitle = SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle();
        File file = new File("src\\NotebankSaveFiles\\" + notebankTitle + ".ser");
        if(file.delete()){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int createNotebank(Notebank notebank) throws SQLException{
        return 0;
    }

    @Override
    public int saveProject(Project project) {
        String fileName = project.getProjectTitle();
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\ProjectSaveFiles\\" + fileName + ".ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(project);
            outputStream.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /SaveFiles/" + fileName + ".ser");
            return 1;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return 0;
        }
    }

    @Override
    public int createProject(Project currentProject) throws SQLException {
        return 0;
    }


    @Override
    public int saveNotebank(Notebank notebank) {
        String fileName = notebank.getNotebankTitle();
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\NotebankSaveFiles\\" + fileName + ".ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(notebank);
            outputStream.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /NotebankSaveFiles/" + fileName + ".ser");
            return 1;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<Project> getAllProject() {
        ArrayList<Project> projects = new ArrayList<>();
        Project tempProject;
        try {
            File directoryPath = new File("src\\ProjectSaveFiles");

            File[] fileList = directoryPath.listFiles();
            assert fileList != null;
            for (File file : fileList) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tempProject = (Project) in.readObject();
                projects.add(tempProject);
                in.close();
                fileIn.close();
            }

            return projects;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Project class not found");
            c.printStackTrace();
            return null;
        } catch (NullPointerException n) {
            System.out.println("Null pointer");
            n.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Notebank> getAllNotebank() {
        ArrayList<Notebank> notebanks = new ArrayList<>();
        Notebank tempNotebank;
        try {
            File directoryPath = new File("src\\NotebankSaveFiles");

            File[] fileList = directoryPath.listFiles();

            for (File file : fileList) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tempNotebank = (Notebank) in.readObject();
                notebanks.add(tempNotebank);
                in.close();
                fileIn.close();
            }

            return notebanks;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Notebank class not found");
            c.printStackTrace();
            return null;
        } catch (NullPointerException n) {
            System.out.println("Null pointer");
            n.printStackTrace();
            return null;
        }
    }

}
