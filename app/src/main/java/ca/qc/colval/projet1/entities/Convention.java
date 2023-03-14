package ca.qc.colval.projet1.entities;

public class Convention {
    private int conventionId;
    private String name;

    public Convention() {

    }

    public Convention(String name) {
        this.name = name;
    }

    public int getConventionId() {
        return conventionId;
    }

    public void setConventionId(int conventionId) {
        this.conventionId = conventionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
