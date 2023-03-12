package ca.qc.colval.projet1.entities;

public class SupplierConvention {

    private int supplierConventionId;
    private int conventionId;
    private int supplierId;

    public SupplierConvention() {

    }


    public SupplierConvention(int supplierConventionId, int conventionId, int supplierId) {
        this.supplierConventionId = supplierConventionId;
        this.conventionId = conventionId;
        this.supplierId = supplierId;
    }

    public int getSupplierConventionId() {
        return supplierConventionId;
    }

    public void setSupplierConventionId(int supplierConventionId) {
        this.supplierConventionId = supplierConventionId;
    }

    public int getConventionId() {
        return conventionId;
    }

    public void setConventionId(int conventionId) {
        this.conventionId = conventionId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
