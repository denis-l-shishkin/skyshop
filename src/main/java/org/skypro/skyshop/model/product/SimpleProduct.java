package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product{
    private int simplePrice;

    public SimpleProduct(String name, int price){
        super(name);
        if (!(price >= 1)) {
            throw new IllegalArgumentException("Стоимость простого товара должна быть больше 0!");
        }
        this.simplePrice = price;
    }
    @Override
    public int getPrice() {
        return simplePrice;
    }
    @Override
    public String toString() {
        return
                name + " : " + simplePrice;
    }
    @Override
    public boolean isSpecial() {
        if (getClass() == SimpleProduct.class) {
            return false;
        } else {
            return true;
        }
    }
}
