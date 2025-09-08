package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected String name;
    protected final UUID id;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым!");
        }
        this.name = name;
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String toString() {
        return
                name;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
