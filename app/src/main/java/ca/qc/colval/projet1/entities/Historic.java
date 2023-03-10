package ca.qc.colval.projet1.entities;

public class Historic {
    private int historicId;
    private int bankAccountId;
    private int projectId;
    private String date;
    private boolean isPaid;

    public Historic(int historicId, int bankAccountId, int projectId, String date, boolean isPaid) {
        this.historicId = historicId;
        this.bankAccountId = bankAccountId;
        this.projectId = projectId;
        this.date = date;
        this.isPaid = isPaid;
    }
}
