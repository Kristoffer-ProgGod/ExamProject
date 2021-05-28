package Application;


import Domain.Project;

import java.io.*;
import java.util.ArrayList;

public class SingleUser implements ProjectStrategy, NotebankStrategy {

    public SingleUser() {

    }

    @Override
    public int deleteProject() {
        return 0;
    }

    @Override
    public int deleteNotebank() {
        return 0;
    }

    @Override
    public int saveProject(Project project) {
        String fileName = project.getProjectTitle();
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\SaveFiles\\" +fileName+ ".ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(project);
            outputStream.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /SaveFiles/"+fileName + ".ser");
            return 1;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return 0;
        }
    }


    @Override
    public int saveNotebank() {
        return 0;
    }

    @Override
    public boolean getAllProject() {
        ArrayList<Project> projects = new ArrayList<>();
        Project tempProject;
        try {
            File directoryPath = new File("src\\SaveFiles");

            File[] fileList = directoryPath.listFiles();

            for (File file: fileList) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempProject = (Project) in.readObject();
            projects.add(tempProject);
            in.close();
            fileIn.close();
            }
            for (Project project: projects){
                System.out.println(project.getProjectTitle());
                System.out.println(project.getProjectId());
                System.out.println(project.getTimeline());
                System.out.println(project.getTimelineTitle() + "\n");
            }

            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return false;
        } catch (NullPointerException n){
            System.out.println("Null pointer");
            n.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean getAllNotebank() {
        return false;
    }
}
