package ca.qc.colval.projet1.entities;

public class Check {
    private int checkId;
    private int expenseId;
    private int checkNum;
    private double amount;
    private int accountId;
    private int projectId;
    private String deadlineDate;
    private int isPaid;


    public Check() {

    }

    public Check(int expenseId, int checkNum, double amount, int accountId, int projectId, String deadlineDate,int isPaid) {
        this.expenseId = expenseId;
        this.checkNum = checkNum;
        this.amount = amount;
        this.accountId = accountId;
        this.projectId = projectId;
        this.deadlineDate = deadlineDate;
        this.isPaid = isPaid;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getCheckNum() {
        return checkNum;
    }
    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    public int getIsPaid() {return isPaid;}

    public void setIsPaid(int isPaid) {isPaid = isPaid;}
}
