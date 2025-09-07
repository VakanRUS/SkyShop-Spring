package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int productPrice;
    private final int discountPercent;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id, name);

        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть меньше либо равна 0!");
        } else productPrice = price;

        if (discount <= 0 || discount >= 100) {
            throw new IllegalArgumentException("Размер скидки должен быть в пределах от 0 до 100 процентов включительно!");
        } else discountPercent = discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getPrice() {
        return productPrice - ((productPrice * discountPercent) / 100);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (скидка -" + discountPercent + "%)";
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return getName().equals(that.getName()) && productPrice == that.productPrice && getDiscountPercent() == that.getDiscountPercent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), productPrice, getDiscountPercent());
    }
}