package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE_PRODUCT = 99;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE_PRODUCT;
    }
    @Override
    public String toString() {
        return
                name + " : Фиксированная цена " + FIX_PRICE_PRODUCT;
    }
    @Override
    public boolean isSpecial() {
        if (getClass() == FixPriceProduct.class) {
            return true;
        } else {
            return false;
        }
    }
}
