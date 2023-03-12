package ca.qc.colval.projet1.entities;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private int supplierID;
    private String name;
    public Supplier() {

    }

    public Supplier(int supplierID, String name) {
        this.supplierID = supplierID;
        this.name = name;

    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
