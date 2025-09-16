package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productsBasket;

    public ProductBasket() {
        this.productsBasket = new HashMap<>();
    }
    public void addProduct(UUID productId) {
        productsBasket.merge(productId, 1, Integer::sum);
    }
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(productsBasket);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasket that = (ProductBasket) o;
        return Objects.equals(productsBasket, that.productsBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productsBasket);
    }
}