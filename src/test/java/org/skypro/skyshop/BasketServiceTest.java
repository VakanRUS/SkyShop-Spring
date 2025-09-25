package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)

public class BasketServiceTest {

    @Mock
    public ProductBasket productBasket;

    @Mock
    public StorageService storageService;

    @InjectMocks
    public BasketService basketService;

    @Test
    public void givenBasket_whenAddingNotExistingElement_thenThrowException() {
        Assertions.assertEquals(true, storageService.getAllElements().isEmpty());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(UUID.randomUUID()));
    }

    @Test
    public void givenEmptyBasket_whenGetUserBasket_thenReturnEmptyBasket() {
        UserBasket result = basketService.getUserBasket();

        Assertions.assertEquals(true, productBasket.getBasket().isEmpty());
        Assertions.assertEquals(true, result.getTotal() == 0);
    }

    @Test
    public void givenStorageWithElements_whenAddingExistedElement_thenCallAddProductMethod() {
        Map<UUID, Searchable> mapElements = fillTestProducts();
        UUID id = mapElements.values().stream()
                .findFirst()
                .get()
                .getId();

        Mockito.when(storageService.getProductById(id))
                .thenReturn(Optional.of((Product) mapElements.values().stream()
                        .findFirst()
                        .get()));
        basketService.addProduct(id);

        Mockito.verify(productBasket, Mockito.times(1)).addProduct(id);
    }

    @Test
    public void givenNotEmptyBasket_whenGetUserBasket_thenReturnBasket() {
        Map<UUID, Searchable> mapElements = fillTestProducts();
        UUID id = mapElements.values().stream().findFirst().get().getId();

        productBasket.addProduct(id);

        UserBasket result = basketService.getUserBasket();
        List<BasketItem> items = result.getBasketItems();
        for (BasketItem basketItem : items) {
            Assertions.assertEquals(500, basketItem.getProduct().getPrice());
        }
    }

    public Map<UUID, Searchable> fillTestProducts() {
        Map<UUID, Searchable> testProducts = new HashMap<>();

        UUID id = UUID.randomUUID();
        Product product1 = new SimpleProduct(id, "Хлеб белый", 60);
        testProducts.put(id, product1);

        id = UUID.randomUUID();
        Product product2 = new SimpleProduct(id, "Хлеб чёрный", 50);
        testProducts.put(id, product2);

        id = UUID.randomUUID();
        Product product3 = new DiscountedProduct(id, "Яблоки", 100, 5);
        testProducts.put(id, product3);

        return testProducts;
    }
}