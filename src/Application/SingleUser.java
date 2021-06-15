package Application;


import Domain.Notebank;
import Domain.Project;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingleUser implements ProjectStrategy, NotebankStrategy {

    public SingleUser() {

    }
    /*
    Select the current Project and removes the Object whilest using its Title to delete .ser file from Savefiles
     */
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
    /*
    Select the current Notebank and removes the Object whilest using its Title to delete .ser file from Savefiles
     */
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
    /*
    Takes the Title entered to create a new .ser File with the Notebank title as its Name
     */
    @Override
    public int createNotebank(Notebank notebank) throws SQLException{
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
    /*
    Edits the selected Project and overwrites its contents and saves it
     */
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
    /*
    Takes the Title entered to create a new .ser File with the Project title as its Name
     */

    @Override
    public int createProject(Project project) throws SQLException {
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
    /*
    Edits the selected Notebank and overwrites its contents and saves it
     */
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
    /*
    Read for existing Project files in the ProjectSaveFiles folder and add them to the ArrayList for updated ListView
     */
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
    /*
    Try to rename an existing Project by replacing the ProjectTitle with a new one and changing Filenames accordingly
     */
    @Override
    public void editProjectTitle(String projectName) throws IOException {
        String oldProjectName = SingletonMediator.getInstance().getCurrentProject().getProjectTitle();
        File oldFile = new File("src\\ProjectSaveFiles\\" + oldProjectName + ".ser");
        File newFile = new File("src\\ProjectSaveFiles\\" + projectName + ".ser");
        /*
        Check if intended filename already exists
         */
        if(newFile.exists()) {
            throw new IOException("file exists");
        }
        /*
        Actual file renaming process
         */
        boolean success = oldFile.renameTo(newFile);
        /*
        Task fail feedback
         */
        if(!success){
            System.out.println("File name not changed.");
        }
        SingletonMediator.getInstance().getCurrentProject().setProjectTitle(projectName);
        oldFile.delete();
        }

    /*
    Read for existing Notebank files in the NotebankSaveFiles folder and add them to the ArrayList for updated ListView
     */
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
    /*
    Try to rename an existing Project by replacing the NotebankTitle with a new one and changing Filenames accordingly
     */
    @Override
    public void editNotebankTitle(String noteBankName) throws IOException {
        String oldNotebankName = SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle();
        File oldFile = new File("src\\NotebankSaveFiles\\" + oldNotebankName + ".ser");
        File newFile = new File("src\\NotebankSaveFiles\\" + noteBankName + ".ser");

        if(newFile.exists()) {
            throw new IOException("file exists");
        }
        boolean success = oldFile.renameTo(newFile);

        if(!success){
            System.out.println("File name not changed.");
        }
        SingletonMediator.getInstance().getCurrentNotebank().setNotebankTitle(noteBankName);

    }

}
