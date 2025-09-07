package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        total = calculateTotal();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }

    private Integer calculateTotal() {
        return basketItems.stream().
                mapToInt(basket -> basket.getProduct().getPrice() * basket.getQuantity())
                .sum();
    }
}