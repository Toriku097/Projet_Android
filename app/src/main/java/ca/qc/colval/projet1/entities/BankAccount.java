package ca.qc.colval.projet1.entities;

public class BankAccount {
    private int accountId;
    private String noAccount;
    private String bank;

    public BankAccount() {

    }

    public BankAccount(int aId,String no,String bank){
        this.accountId = aId;
        this.noAccount = no;
        this.bank = bank;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNoAccount() {
        return noAccount;
    }

    public void setNoAccount(String noAccount) {
        this.noAccount = noAccount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
