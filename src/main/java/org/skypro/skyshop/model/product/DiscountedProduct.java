package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountInPercent;

    public DiscountedProduct(String name, int basePrise, int discountInPercent) {
        super(name);
        if (!(basePrise >= 1)) {
            throw new IllegalArgumentException("Стоимость дисконтного товара должна быть больше 0!");
        }
        if (!(discountInPercent <= 100 && discountInPercent >= 0)){
            throw new IllegalArgumentException("Скидка в процентах должна быть в диаппазоне от 0 до 100!");
        }
        this.basePrice = basePrise;
        this.discountInPercent = discountInPercent;
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice * discountInPercent/100);
    }
    @Override
    public String toString() {
        return
                name + " : " + basePrice + "(" + discountInPercent + "%)";
    }
    @Override
    public boolean isSpecial() {
        if (getClass() == DiscountedProduct.class) {
            return true;
        } else {
            return false;
        }
    }
}
