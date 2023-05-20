package ca.qc.colval.projet1.entities;

public class Contract {
    private String desc;
    private String supplier;
    private double amount;
    private String _id;

    public Contract(String _id, String desc, String supplier, double amount) {
        this._id = _id;
        this.desc = desc;
        this.supplier = supplier;
        this.amount = amount;
    }
    public Contract(String desc, String supplier, double amount) {
        this.desc = desc;
        this.supplier = supplier;
        this.amount = amount;
    }

    public String get_id(){
        return _id;
    }

    public String getDesc() {
        return desc;
    }

    public String getSupplier() {
        return supplier;
    }
}
