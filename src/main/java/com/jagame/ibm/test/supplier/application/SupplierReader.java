package com.jagame.ibm.test.supplier.application;

import com.jagame.ibm.test.supplier.domain.Supplier;

import java.util.List;

public interface SupplierReader {
    List<Supplier> findSuppliersByClientId(int idCliente);
}
