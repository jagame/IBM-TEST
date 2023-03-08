package com.jagame.ibm.test.supplier.infrastructure;

import com.jagame.ibm.test.supplier.application.SupplierMigrationException;
import com.jagame.ibm.test.supplier.application.SupplierReader;
import com.jagame.ibm.test.supplier.domain.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSupplierReader implements SupplierReader {

    private final ConnectionSupplier connectionSupplier;

    public JdbcSupplierReader(ConnectionSupplier connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    @Override
    public List<Supplier> findSuppliersByClientId(int clientId) {
        try (
                var connection = connectionSupplier.newConnection();
                var statement = connection.prepareStatement("SELECT * FROM Proveedor WHERE id_cliente = ?")
        ) {
            statement.setInt(1, clientId);
            var resultSet = statement.executeQuery();
            var resultList = new ArrayList<Supplier>();
            while (resultSet.next()) {
                resultList.add(extractSupplier(resultSet));
            }
            return resultList;
        } catch (SQLException ex) {
            throw new SupplierMigrationException(ex);
        }
    }

    private Supplier extractSupplier(ResultSet resultSet) throws SQLException {
        var supplier = new Supplier();
        supplier.setSupplierId(resultSet.getInt(1));
        supplier.setName(resultSet.getString(2));
        supplier.setRegistrationDate(resultSet.getDate(3).toLocalDate());
        supplier.setClientId(resultSet.getInt(4));

        return supplier;
    }

}
