package Application;

import Database.MyDatabase;
import Domain.Notebank;
import Domain.Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiUser implements ProjectStrategy, NotebankStrategy, Serializable {


    public static final int DEFAULT_BUFFER_SIZE = 8192;

    @Override
    public int deleteProject() {
        return 0;
    }

    @Override
    public int deleteNotebank() {
        return 0;
    }

    @Override
    public int saveNotebank(Notebank notebank) throws SQLException {
        int i = 0;
        String fileName = notebank.getNotebankTitle();
        byte[] fileContents;
        File tempFile = new File("src\\NotebankSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {

            notebank.setNotebankId();
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

    @Override
    public int saveProject(Project project) throws SQLException {
        int i = 0;
        String fileName = project.getProjectTitle();
        byte[] fileContents;
        File tempFile = new File("src\\ProjectSaveFiles\\" + fileName + ".ser");
        Connection connection;

        try {

            project.setProjectId();
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

    @Override
    public ArrayList<Project> getAllProject() throws SQLException {
        ArrayList<Project> projects = new ArrayList<Project>();
        Project tempProject;
        String projectTitle;
        File tempFile;
        byte[] input;
        try{

        Connection connection = MyDatabase.openConnection();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PreparedStatement ps = connection.prepareStatement("SELECT fldProjectSaveFile, fldProjectTitle from tbl_Project");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Blob blob = rs.getBlob("fldProjectSaveFile");
            baos.write( blob.getBytes(1, (int) blob.length()));
            blob.free();
            input = baos.toByteArray();
            projectTitle = rs.getString("fldProjectTitle").replaceAll("\\s+$", "");
            tempFile = new File("src\\ProjectSaveFiles\\" + projectTitle + ".ser");
            System.out.println(input);

            FileOutputStream fileOut = new FileOutputStream(tempFile);
            fileOut.write(input);
            fileOut.close();

            FileInputStream fileIn = new FileInputStream(tempFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempProject = (Project) in.readObject();
            projects.add(tempProject);
            in.close();
            fileIn.close();
        }
            return projects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Notebank> getAllNotebank() {
        ArrayList<Notebank> notebanks = new ArrayList<Notebank>();
        Notebank tempNotebank;
        String notebankTitle;
        File tempFile;
        byte[] input;
        try{

            Connection connection = MyDatabase.openConnection();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PreparedStatement ps = connection.prepareStatement("SELECT fldNotebankSaveFile, fldNotebankTitle from tbl_Notebank");

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Blob blob = rs.getBlob("fldNotebankSaveFile");
                baos.write( blob.getBytes(1, (int) blob.length()));
                blob.free();
                input = baos.toByteArray();
                notebankTitle = rs.getString("fldNotebankTitle").replaceAll("\\s+$", "");
                tempFile = new File("src\\NotebankSaveFiles\\" + notebankTitle + ".ser");
                System.out.println(input);

                FileOutputStream fileOut = new FileOutputStream(tempFile);
                fileOut.write(input);
                fileOut.close();

                FileInputStream fileIn = new FileInputStream(tempFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                tempNotebank = (Notebank) in.readObject();
                notebanks.add(tempNotebank);
                in.close();
                fileIn.close();
            }
            return notebanks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

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
