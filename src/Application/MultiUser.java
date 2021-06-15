package Application;

import Database.MyDatabase;
import Domain.Notebank;
import Domain.Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class MultiUser implements ProjectStrategy, NotebankStrategy, Serializable {


    //Deletes the currently selected projects associated file and entry in the database
    @Override
    public int deleteProject() throws SQLException {
        Connection connection = MyDatabase.openConnection();
        String projectTitle = SingletonMediator.getInstance().getCurrentProject().getProjectTitle();
        PreparedStatement ps = connection.prepareStatement("Delete from tbl_Project where fldProjectTitle = ?");
        ps.setString(1, projectTitle);
        ps.execute();
        connection.close();

        HomepageController.projects.remove(SingletonMediator.getInstance().getCurrentProject());
        File file = new File("src\\ProjectSaveFiles\\" + projectTitle + ".ser");
        if(file.delete()){
            return 1;
        }else {
            return 0;
        }
    }

    //Deletes the currently selected notebanks associated file and entry in the database
    @Override
    public int deleteNotebank() throws SQLException {
        Connection connection = MyDatabase.openConnection();
        String notebankTitle = SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle();
        PreparedStatement ps = connection.prepareStatement("Delete from tbl_Notebank where fldNotebankTitle = ?");
        ps.setString(1, notebankTitle);
        ps.execute();
        connection.close();

        HomepageController.notebanks.remove(SingletonMediator.getInstance().getCurrentNotebank());
        File file = new File("src\\NotebankSaveFiles\\" + notebankTitle + ".ser");
        if(file.delete()){
            return 1;
        }else {
            return 0;
        }
    }

    /*
    @param Notebank the currently selected notebank
    Takes a newly created notebank object and generates a .ser file for it and adds it to the database.
     */
    @Override
    public int createNotebank(Notebank notebank) throws SQLException {
        int i = 0;
        String fileName = notebank.getNotebankTitle();
        byte[] fileContents;
        File tempFile = new File("src\\NotebankSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {
            //Saves the project as a serialized file in the given directory
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(notebank);
            outputStream.close();
            fileOut.close();

            //Reads the serialized file contents
            fileContents = Files.readAllBytes(Paths.get(tempFile.getAbsolutePath()));
            ByteArrayInputStream bais = new ByteArrayInputStream(fileContents);


            //Writes the contents to the database
            connection = MyDatabase.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tbl_Notebank VALUES (?,?,?)");
            preparedStatement.setInt(1, notebank.getNotebankId());
            preparedStatement.setBlob(2, bais);
            preparedStatement.setString(3, notebank.getNotebankTitle());
            i = preparedStatement.executeUpdate();
            MyDatabase.closeConnection(connection);
            System.out.println("Serialized data is saved in /NotebankSaveFiles/" + fileName + ".ser and in db_Timeline, tbl_Notebank");
            return i;
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return i;
        }
    }

    /*
    @param Notebank the currently selected notebank
    Takes then notebanks and updates it's associated .ser file (based on name of the notebank) and database entry (based on the notebank id)
     */
    @Override
    public int saveNotebank(Notebank notebank) throws SQLException {
        int i = 0;
        String fileName = notebank.getNotebankTitle();
        byte[] fileContents;
        File tempFile = new File("src\\NotebankSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {
            //Saves the project as a serialized file in the given directory
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(notebank);
            outputStream.close();
            fileOut.close();

            //Reads the serialized file contents
            fileContents = Files.readAllBytes(Paths.get(tempFile.getAbsolutePath()));
            ByteArrayInputStream bais = new ByteArrayInputStream(fileContents);


            //Writes the contents to the database
            connection = MyDatabase.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tbl_Notebank set fldNotebankSaveFile = ?, fldNotebankTitle = ? where fldNotebankId = ?");
            preparedStatement.setInt(3, notebank.getNotebankId());
            preparedStatement.setBlob(1, bais);
            preparedStatement.setString(2, notebank.getNotebankTitle());
            i = preparedStatement.executeUpdate();
            MyDatabase.closeConnection(connection);
            System.out.println("Serialized data is saved in /NotebankSaveFiles/" + fileName + ".ser and in db_Timeline, tbl_Notebank");
            return i;
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return i;
        }
    }

    /*
    @param Project the currently selected project to be created
    Takes a newly created Project object and generates a .ser file and database entry for it.
     */
    public int createProject(Project project) throws SQLException {
        int i = 0;
        String fileName = project.getProjectTitle();
        byte[] fileContents;
        File tempFile = new File("src\\ProjectSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {
            //Saves the project as a serialized file in the given directory
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(project);
            outputStream.close();
            fileOut.close();

            //Reads the serialized file contents
            fileContents = Files.readAllBytes(Paths.get(tempFile.getAbsolutePath()));
            ByteArrayInputStream bais = new ByteArrayInputStream(fileContents);


            //Writes the contents to the database
            connection = MyDatabase.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tbl_Project VALUES (?,?,?)");
            preparedStatement.setInt(1, project.getProjectId());
            preparedStatement.setBlob(2, bais);
            preparedStatement.setString(3, project.getProjectTitle());
            i = preparedStatement.executeUpdate();
            MyDatabase.closeConnection(connection);
            System.out.println("Serialized data is saved in /ProjectSaveFiles/" + fileName + ".ser and in db_Timeline, tbl_Project");
            return i;
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return i;
        }
    }

    /*
    @param Project the currently selected project
    Takes the project and updates it's associated .ser file (based on name) and database entry (based on project id)
     */
    @Override
    public int saveProject(Project project) throws SQLException {
        int i = 0;
        String fileName = project.getProjectTitle();
        byte[] fileContents;
        File tempFile = new File("src\\ProjectSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {
            //Saves the project as a serialized file in the given directory
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(project);
            outputStream.close();
            fileOut.close();

            //Reads the serialized file contents
            fileContents = Files.readAllBytes(Paths.get(tempFile.getAbsolutePath()));
            ByteArrayInputStream bais = new ByteArrayInputStream(fileContents);


            //Writes the contents to the database
            connection = MyDatabase.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tbl_Project set fldProjectSaveFile = ?, fldProjectTitle = ? where fldProjectId = ?");
            preparedStatement.setInt(3, project.getProjectId());
            preparedStatement.setBlob(1, bais);
            preparedStatement.setString(2, project.getProjectTitle());
            i = preparedStatement.executeUpdate();
            MyDatabase.closeConnection(connection);
            System.out.println("Serialized data is saved in /ProjectSaveFiles/" + fileName + ".ser and in db_Timeline, tbl_Project");
            return i;
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return i;
        }
    }

    /*
    Loads all the projects saved in the database and deserializes them to project files
    Uses a blob and ByteArrayOutputStream to read the blob data and add it to the file before it is deserialized
     */
    @Override
    public ArrayList<Project> getAllProject()  {
        ArrayList<Project> projects = new ArrayList<Project>();
        Project tempProject;
        String projectTitle;
        File tempFile;
        byte[] input;
        try {

            Connection connection = MyDatabase.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT fldProjectSaveFile, fldProjectTitle from tbl_Project");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Blob blob = rs.getBlob("fldProjectSaveFile");
                baos.write(blob.getBytes(1, (int) blob.length()));
                input = baos.toByteArray();
                projectTitle = rs.getString("fldProjectTitle").replaceAll("\\s+$", "");
                tempFile = new File("src\\ProjectSaveFiles\\" + projectTitle + ".ser");
                System.out.println(projectTitle + " " + input);

                FileOutputStream fileOut = new FileOutputStream(tempFile);
                fileOut.write(input);

                FileInputStream fileIn = new FileInputStream(tempFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tempProject = (Project) in.readObject();
                projects.add(tempProject);
                blob.free();
                baos.close();
                fileOut.close();
                in.close();
                fileIn.close();
            }
            return projects;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void editProjectTitle(String projectName) throws IOException {
        String oldProjectName = SingletonMediator.getInstance().getCurrentProject().getProjectTitle();
        File oldFile = new File("src\\ProjectSaveFiles\\" + oldProjectName + ".ser");
        File newFile = new File("src\\ProjectSaveFiles\\" + projectName + ".ser");

        if(newFile.exists()) {
            throw new IOException("file exists");
        }
        boolean success = oldFile.renameTo(newFile);

        if(!success){
            System.out.println("File name not changed.");
        }
        SingletonMediator.getInstance().getCurrentProject().setProjectTitle(projectName);
        oldFile.delete();
    }



    @Override
    public ArrayList<Notebank> getAllNotebank() {
        ArrayList<Notebank> notebanks = new ArrayList<Notebank>();
        Notebank tempNotebank;
        String notebankTitle;
        File tempFile;
        byte[] input;
        try {

            Connection connection = MyDatabase.openConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT fldNotebankSaveFile, fldNotebankTitle from tbl_Notebank");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Blob blob = rs.getBlob("fldNotebankSaveFile");
                baos.write(blob.getBytes(1, (int) blob.length()));
                input = baos.toByteArray();
                notebankTitle = rs.getString("fldNotebankTitle").replaceAll("\\s+$", "");
                tempFile = new File("src\\NotebankSaveFiles\\" + notebankTitle + ".ser");
                System.out.println(input);

                FileOutputStream fileOut = new FileOutputStream(tempFile);
                fileOut.write(input);

                FileInputStream fileIn = new FileInputStream(tempFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tempNotebank = (Notebank) in.readObject();
                notebanks.add(tempNotebank);
                blob.free();
                fileOut.close();
                in.close();
                fileIn.close();
            }
            return notebanks;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    @Override
    public void editNotebankTitle(String notebankName) throws IOException {


        String oldNotebankName = SingletonMediator.getInstance().getCurrentNotebank().getNotebankTitle();
        File oldFile = new File("src\\NotebankSaveFiles\\" + oldNotebankName + ".ser");
        File newFile = new File("src\\NotebankSaveFiles\\" + notebankName + ".ser");

        if(newFile.exists()) {
            throw new IOException("file exists");
        }
        boolean success = oldFile.renameTo(newFile);

        if(!success){
            System.out.println("File name not changed.");
        }
        SingletonMediator.getInstance().getCurrentNotebank().setNotebankTitle(notebankName);
        oldFile.delete();
    }


    private static String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }

        return sb.toString();
    }
}
