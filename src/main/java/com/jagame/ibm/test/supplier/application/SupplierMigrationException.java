package com.jagame.ibm.test.supplier.application;

public class SupplierMigrationException extends RuntimeException {
    public SupplierMigrationException(Throwable cause) {
        super("No se ha podido completar la migración de proveedores", cause);
    }
}
