package ca.qc.colval.projet1.entities;

public class BankAccount {
    private int accountId;
    private String noAccount;
    private String bank;

    private float funds;

    public BankAccount() {

    }

    public BankAccount(int aId,String no,String bank, float funds){
        this.accountId = aId;
        this.noAccount = no;
        this.bank = bank;
        this.funds = funds;
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

    public float getFunds() {
        return funds;
    }

    public void setFunds(float funds) {
        this.funds = funds;
    }
}
