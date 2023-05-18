package ca.qc.colval.projet1.entities;

public class Convention {
    private int conventionId;
    private String name;

    private int amount;

    public Convention() {

    }

    public Convention(int conventionId, String name, int amount) {
        this.conventionId = conventionId;
        this.name = name;
        this.amount = amount;
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

    //getters and setters for amount
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
