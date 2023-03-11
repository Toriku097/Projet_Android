package ca.qc.colval.projet1.entities;

public class Deadline {
    private int checkNum;
    private String account;
    private double amount;
    private String expenseType;
    private String dae;
    private String projectName;

    public Deadline(int checkNum, String account, double amount, String expenseType, String dae, String projectName) {
        this.checkNum = checkNum;
        this.account = account;
        this.amount = amount;
        this.expenseType = expenseType;
        this.dae = dae;
        this.projectName = projectName;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDae() {
        return dae;
    }

    public void setDae(String dae) {
        this.dae = dae;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
