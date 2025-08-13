package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class FixPriceProduct extends Product {
    private final int FIXED_PRICE = 50;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FixPriceProduct that = (FixPriceProduct) o;
        return getName().equals(that.getName()) && getPrice() == that.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), FIXED_PRICE);
    }
}