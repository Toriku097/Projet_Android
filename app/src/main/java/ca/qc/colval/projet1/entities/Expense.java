package ca.qc.colval.projet1.entities;

public class Expense {
    private int expenseId;
    private String expenseType;
    private double amount;
    private String paymentMethod;
    private int bankAccountId;
    private int supplierId;
    private int projectId;

    public Expense() {

    }

    public Expense(String expenseType, double amount, String paymentMethod, int bankAccountId, int supplierId, int projectId) {

        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.bankAccountId = bankAccountId;
        this.supplierId = supplierId;
        this.projectId = projectId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
