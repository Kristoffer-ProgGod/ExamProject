package Application;

import Domain.Notebank;
import Domain.Project;

public abstract class Mediator {
    private Project currentProject;
    private Notebank currentNotebank;
    private ProjectStrategy currentProjectStrategy;
    private NotebankStrategy currentNotebankStrategy;


    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public Notebank getCurrentNotebank() {
        return currentNotebank;
    }

    public void setCurrentNotebank(Notebank currentNotebank) {
        this.currentNotebank = currentNotebank;
    }

    public ProjectStrategy getCurrentProjectStrategy() {
        return currentProjectStrategy;
    }

    public void setCurrentProjectStrategy(ProjectStrategy currentProjectStrategy) {
        this.currentProjectStrategy = currentProjectStrategy;
    }

    public NotebankStrategy getCurrentNotebankStrategy() {
        return currentNotebankStrategy;
    }

    public void setCurrentNotebankStrategy(NotebankStrategy currentNotebankStrategy) {
        this.currentNotebankStrategy = currentNotebankStrategy;
    }
}
