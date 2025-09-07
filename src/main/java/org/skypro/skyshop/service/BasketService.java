package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException();
        } else {
            productBasket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        List<BasketItem> list = productBasket.getBasket().entrySet().stream()
                .map(element -> {
                    Product product = storageService.getProductById(element.getKey()).orElseThrow();
                    return new BasketItem(product, element.getValue());
                })
                .toList();
        return new UserBasket(list);
    }
}