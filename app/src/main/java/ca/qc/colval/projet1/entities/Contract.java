package ca.qc.colval.projet1.entities;

public class Contract {
    private String desc;
    private String supplier;
    private double amount;

    public Contract() {

    }

    public Contract(String desc, String supplier, double amount) {
        this.desc = desc;
        this.supplier = supplier;
        this.amount = amount;
    }

}
