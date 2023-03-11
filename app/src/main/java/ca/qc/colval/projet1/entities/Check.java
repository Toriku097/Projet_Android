package ca.qc.colval.projet1.entities;

public class Check {
    private int CheckId;
    private int expenseId;
    private int checkNum;
    private int projectId;
    private String deadlineDate;

    public Check(int checkId, int expenseId, int checkNum, int projectId, String deadlineDate) {
        CheckId = checkId;
        this.expenseId = expenseId;
        this.checkNum = checkNum;
        this.projectId = projectId;
        this.deadlineDate = deadlineDate;
    }
}
