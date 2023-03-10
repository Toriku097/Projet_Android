package ca.qc.colval.projet1.entities;

public class Expense {
    private int expenseId;
    private String expenseType;
    private double amount;
    private String paymentMethod;
    private int bankAccountId;
    private int supplierId;

    public Expense(int expenseId, String expenseType, double amount, String paymentMethod, int bankAccountId, int supplierId) {
        this.expenseId = expenseId;
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.bankAccountId = bankAccountId;
        this.supplierId = supplierId;
    }
}
