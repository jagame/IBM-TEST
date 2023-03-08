package com.jagame.ibm.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    private static final int RECORDS_OF_CLIENT_101 = 5;
    private static final int NUMBER_OF_FIELDS = 4;

    @Test
    void testWhitoutArgs() throws IOException {
        Main.main();
        assertFalse(new File("suppliers.csv").exists(), "new File(\"suppliers.csv\").exists()");
    }

    @Test
    void testWhitIdClientWhitoutSupplier() throws IOException, SQLException {
        Main.main("1");
        var lines = Files.readAllLines(new File("suppliers.csv").toPath());
        assertEquals(1, lines.size());
        assertEquals("id_proveedor;nombre;fecha_alta;id_cliente", lines.get(0));
    }

    @Test
    void testWhitIdClientWhitSupplier() throws IOException {
        Main.main("101");
        var lines = Files.readAllLines(new File("suppliers.csv").toPath());
        assertEquals(RECORDS_OF_CLIENT_101, lines.size());
        assertEquals("id_proveedor;nombre;fecha_alta;id_cliente", lines.get(0));
        assertEquals(NUMBER_OF_FIELDS, lines.get(1).split(";").length);
    }

}
