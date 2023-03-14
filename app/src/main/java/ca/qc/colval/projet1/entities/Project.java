package ca.qc.colval.projet1.entities;

public class Project {
    private int projectId;
    private String name;
    private double totalExpenses;

    public Project() {

    }

    public Project(String name, double totalExpenses) {
        this.name = name;
        this.totalExpenses = totalExpenses;
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

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
