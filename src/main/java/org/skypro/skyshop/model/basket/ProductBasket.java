package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket() {
        this.productBasket = new HashMap<>();
    }

    public void addProduct(UUID id) {
        productBasket.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}