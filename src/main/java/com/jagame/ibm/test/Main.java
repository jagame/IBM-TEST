package com.jagame.ibm.test;

import com.jagame.ibm.test.supplier.application.SupplierMigration;
import com.jagame.ibm.test.supplier.infrastructure.ConnectionSupplier;
import com.jagame.ibm.test.supplier.infrastructure.JdbcSupplierReader;
import com.jagame.ibm.test.supplier.infrastructure.SupplierFlatFileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) throws IOException {
        var outputFile = getCleanedFile("suppliers.csv");
        if (args.length == 0) {
            LOGGER.info("Es necesario especificar un id_cliente");
            return;
        }
        var connectionSupplier = ConnectionSupplier.create("db.properties");
        if (isDb2DatabaseInUse(connectionSupplier)) {
            H2DatabaseInitializer.tryInitialize(connectionSupplier);
        }

        var reader = new JdbcSupplierReader(connectionSupplier);
        var writer = new SupplierFlatFileWriter(outputFile);
        var suppliersMigration = new SupplierMigration(reader, writer);

        var clientId = toInt(args[0]);
        clientId.ifPresent(suppliersMigration::migrateSupplierByClientId);
    }
    private static File getCleanedFile(String fileName) {
        var file = new File(fileName);
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return file;
    }


    private static boolean isDb2DatabaseInUse(ConnectionSupplier connectionSupplier) {
        return connectionSupplier.getConnectionUrl().startsWith("jdbc:h2");
    }

    private static Optional<Integer> toInt(String arg) {
        try {
            return Optional.of(Integer.parseInt(arg));
        } catch (NumberFormatException ex) {
            LOGGER.log(Level.SEVERE, () -> "El argumento " + arg + " no es reconocible como un número entero");
            return Optional.empty();
        }
    }

}