package org.skypro.skyshop.model.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserBasket {
    private final List<BasketItem> items;
    private final double total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = calculateTotal(items);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    private double calculateTotal(List<BasketItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return Double.compare(total, that.total) == 0 && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, total);
    }
}
