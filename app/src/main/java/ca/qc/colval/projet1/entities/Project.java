package ca.qc.colval.projet1.entities;

public class Project {
    private int projectId;
    private String name;

    public Project() {

    }

    public Project(int projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
