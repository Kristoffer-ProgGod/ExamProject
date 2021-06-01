package Application;

import Database.MyDatabase;
import Domain.Notebank;
import Domain.Project;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MultiUser implements ProjectStrategy, NotebankStrategy {


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
        return 0;
    }

    @Override
    public int saveProject(Project project) throws SQLException {
        int i = 0;
        String fileName = project.getProjectTitle();
        String fileContents;
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
            fileContents = readFile(tempFile);

            //Writes the contents to the database
            connection = MyDatabase.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tbl_Project VALUES (?,?)");
            preparedStatement.setInt(1, project.getProjectId());
            preparedStatement.setString(2, fileContents);
            i = preparedStatement.executeUpdate();
            MyDatabase.closeConnection(connection);
            System.out.println("Serialized data is saved in /SaveFiles/" + fileName + ".ser and in db_Timeline, tbl_Project");
            return i;
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return i;
        }
    }

    @Override
    public ArrayList<Project> getAllProject() {
        return null;
    }

    @Override
    public ArrayList<Notebank> getAllNotebank() {
        return null;
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
