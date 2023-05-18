package ca.qc.colval.projet1.entities;

public class Contract {
    private int contractId;
    private String name;

    private int amount;

    public Contract() {

    }

    public Contract(int conventionId, String name, int amount) {
        this.contractId = conventionId;
        this.name = name;
        this.amount = amount;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
