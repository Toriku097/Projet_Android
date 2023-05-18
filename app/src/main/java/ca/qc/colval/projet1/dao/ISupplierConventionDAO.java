package ca.qc.colval.projet1.dao;

import java.util.List;
import ca.qc.colval.projet1.entities.SupplierConvention;

public interface ISupplierConventionDAO {

    List<SupplierConvention> getAllSupplierConventions();

    SupplierConvention getSupplierConventionById(int id);

    SupplierConvention addSupplierConvention(SupplierConvention supplierConvention);

//    SupplierConvention updateSupplierConventionById(int id, SupplierConvention supplierConvention);
//
//    SupplierConvention deleteSupplierConventionById(int id);
}
