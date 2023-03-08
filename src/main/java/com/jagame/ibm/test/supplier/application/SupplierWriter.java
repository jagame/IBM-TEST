package com.jagame.ibm.test.supplier.application;

import com.jagame.ibm.test.supplier.domain.Supplier;

import java.util.Collection;

public interface SupplierWriter {

    void writeSuppliers(Collection<Supplier> suppliers);
}
