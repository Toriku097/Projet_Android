package ca.qc.colval.projet1.entities;

public class BankAccount {
    private int accountId;
    private String noAccount;
    private String bank;

    BankAccount(int aId,String no,String bank){
        this.accountId = aId;
        this.noAccount = no;
        this.bank = bank;
    }
}
