package com.jagame.ibm.test.supplier.infrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSupplier {

    private final Properties properties;

    public static ConnectionSupplier create(String propertiesFileName) throws IOException {
        var properties = new Properties();
        try (var fileInputStream = new FileInputStream(propertiesFileName)) {
            properties.load(fileInputStream);
        }
        return new ConnectionSupplier(properties);
    }

    private ConnectionSupplier(Properties properties) {
        this.properties = properties;
    }

    public Connection newConnection() throws SQLException {
        if (getConnectionUrl().startsWith("jdbc:h2")) {
            return DriverManager.getConnection(
                    getConnectionUrl()
            );
        } else {
            return DriverManager.getConnection(
                    getConnectionUrl(),
                    getUsername(),
                    getPassword()
            );
        }
    }

    public String getConnectionUrl() {
        return properties.getProperty("url");
    }

    private String getUsername() {
        return properties.getProperty("username");
    }

    private String getPassword() {
        return properties.getProperty("password");
    }
}
