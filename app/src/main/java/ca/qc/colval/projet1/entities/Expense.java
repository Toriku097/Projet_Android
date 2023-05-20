package ca.qc.colval.projet1.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    private String _id;
    private String expenseType;
    private double amount;
    private String paymentMethod;
    private String bankAccount;
    private String supplier;
    private String project;
    private String date;
    private boolean isPaid;

    public Expense() {
    }

    public Expense(String expenseType, double amount, String paymentMethod, String bankAccount, String supplier, String project, String date) {
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.supplier = supplier;
        this.project = project;
        this.bankAccount = bankAccount;
        this.date = date;
        if (paymentMethod.equals("Chèque")){
            isPaid = false;
        } else {
            isPaid = true;
        }
    }
    public Expense(String _id,String expenseType, double amount, String paymentMethod, String bankAccount, String supplier, String project, String date) {
        this._id = _id;
        this.expenseType = expenseType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.supplier = supplier;
        this.project = project;
        this.bankAccount = bankAccount;
        this.date = date;
        if (paymentMethod.equals("Chèque")){
            isPaid = false;
        } else {
            isPaid = true;
        }
    }

    //convert date to date object
    public Date getDateObject(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObject = null;
        try {
            dateObject = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateObject;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(){
        this.isPaid = true;
    }
    public String getExpenseId(){
        return _id;
    }
}
