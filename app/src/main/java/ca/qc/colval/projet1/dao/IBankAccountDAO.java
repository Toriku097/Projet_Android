package ca.qc.colval.projet1.dao;

import java.util.List;

import ca.qc.colval.projet1.entities.BankAccount;

public interface IBankAccountDAO {

    List<BankAccount> getAllBankAccounts();
    BankAccount getBankAccountbyId(int id);
    BankAccount addBankAccount(BankAccount account);
    BankAccount updateBankAccountbyId(int id, BankAccount account);
    BankAccount deleteBankAccountbyId(int id);
}
