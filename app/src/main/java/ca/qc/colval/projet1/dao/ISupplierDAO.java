package ca.qc.colval.projet1.dao;

import java.util.List;


import ca.qc.colval.projet1.entities.Supplier;

public interface ISupplierDAO {

    List<Supplier> getAllSuppliers();
    Supplier getSupplierbyId(int id);
    Supplier addSupplier(Supplier supplier);
    Supplier updateSupplierbyId(int id, Supplier supplier);
    Supplier deleteSupplierbyId(int id);
}