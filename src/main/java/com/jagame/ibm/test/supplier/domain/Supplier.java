package com.jagame.ibm.test.supplier.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Supplier {

    private Integer supplierId;
    private String name;
    private LocalDate registrationDate;
    private Integer clientId;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId) && Objects.equals(name, supplier.name) && Objects.equals(registrationDate, supplier.registrationDate) && Objects.equals(clientId, supplier.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, name, registrationDate, clientId);
    }

}
