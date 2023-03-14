package ca.qc.colval.projet1.control;

import android.database.Cursor;

import java.util.List;

import ca.qc.colval.projet1.entities.*;
import ca.qc.colval.projet1.dao.*;

public class SupplierConventionControl {
    SupplierConventionDAO dao;

    public SupplierConvention AddConventiontoSupplier(Supplier supplier, Convention convention) {
        SupplierConvention sc = new SupplierConvention();

        sc.setSupplierId(supplier.getSupplierID());
        sc.setConventionId(convention.getConventionId());

        dao.addSupplierConvention(sc);
        return sc;
    }
}
