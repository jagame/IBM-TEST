package com.jagame.ibm.test.supplier.application;

import com.jagame.ibm.test.supplier.domain.Supplier;

import java.util.List;
import java.util.logging.Logger;

public class SupplierMigration {

    private static final Logger LOGGER = Logger.getLogger(SupplierMigration.class.getName());

    private final SupplierReader reader;
    private final SupplierWriter writer;

    public SupplierMigration(SupplierReader reader, SupplierWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void migrateSupplierByClientId(int clientId) {
        List<Supplier> suppliers = reader.findSuppliersByClientId(clientId);
        if (suppliers.isEmpty()) {
            LOGGER.info(() -> "El cliente " + clientId + " no tiene proveedores asignados");
        }
        writer.writeSuppliers(suppliers);
    }

}
