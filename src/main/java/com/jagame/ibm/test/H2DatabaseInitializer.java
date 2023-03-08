package com.jagame.ibm.test;

import com.jagame.ibm.test.supplier.infrastructure.ConnectionSupplier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class H2DatabaseInitializer {

    private H2DatabaseInitializer() {
    }

    public static void tryInitialize(ConnectionSupplier connectionSupplier) {
        if (new File(getH2DbFileName(connectionSupplier)).exists()) {
            return;
        }

        try (
                var connection = connectionSupplier.newConnection();
                var statement = connection.createStatement();
        ) {
            var sqlLines = Files.readAllLines(new File("script.sql").toPath());
            for (String sentence : sqlLines) {
                statement.executeUpdate(sentence.substring(0, sentence.length() - 1));
            }
        } catch (IOException | SQLException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static String getH2DbFileName(ConnectionSupplier connectionSupplier) {
        var connectionUrl = connectionSupplier.getConnectionUrl();
        return connectionUrl.substring(10, connectionUrl.indexOf(';')) + ".mv.db";
    }

}
