package com.jagame.ibm.test.supplier.infrastructure;

import com.jagame.ibm.test.supplier.application.SupplierMigrationException;
import com.jagame.ibm.test.supplier.application.SupplierWriter;
import com.jagame.ibm.test.supplier.domain.Supplier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplierFlatFileWriter implements SupplierWriter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final File file;

    public SupplierFlatFileWriter(File file) {
        this.file = file;
    }

    @Override
    public void writeSuppliers(Collection<Supplier> suppliers) {
        try (var writer = new FileWriter(file)) {
            writer.append("id_proveedor;nombre;fecha_alta;id_cliente");
            for (Supplier supplier : suppliers) {
                writer.append("\n");
                String s = toFlatRow(supplier);
                writer.write(s);
            }
        } catch (IOException e) {
            throw new SupplierMigrationException(e);
        }
    }

    private String toFlatRow(Supplier supplier) {
        var formattedRegistrationDate = DATE_FORMATTER.format(supplier.getRegistrationDate());
        return Stream.of(supplier.getSupplierId(), supplier.getName(), formattedRegistrationDate, supplier.getClientId())
                .map(Object::toString)
                .collect(Collectors.joining(";"));
    }

}
