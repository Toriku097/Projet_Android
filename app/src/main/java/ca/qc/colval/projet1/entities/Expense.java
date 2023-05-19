package ca.qc.colval.projet1.entities;

public class Expense {
    private String expenseType;
    private double amount;
    private String paymentMethod;
    private String bankAccount;
    private String supplier;
    private String project;
    private String date;

    public Expense() {
    }

    public Expense(String expenseType, double amount, String paymentMethod, String bankAccount, String supplier, String project, String date) {
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.bankAccount = bankAccount;
        this.supplier = supplier;
        this.project = project;
        this.date = date;
    }
}
