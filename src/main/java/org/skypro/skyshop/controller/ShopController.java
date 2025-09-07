package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;
    private final ProductBasket productBasket;

    public ShopController(ProductBasket productBasket, BasketService basketService, SearchService searchService, StorageService storageService) {
        this.productBasket = productBasket;
        this.basketService = basketService;
        this.searchService = searchService;
        this.storageService = storageService;
    }

    @GetMapping
    public String welcome() {
        return """
                /products - получить список продуктов
                /articles - получить список статей
                /all - получить полный список всего сразу
                /search - поиск по ключевому слову
                /basket/{id} - добавить продукт по идентификатору взятому со страничку /products
                /basket - вывести товары, добавленные в корзину
                """;
    }

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts() {
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getAllArticles() {
        return storageService.getArticles();
    }

    @GetMapping("/all")
    public Map<UUID, Searchable> getAllElements() {
        return storageService.getAllElements();
    }

    @GetMapping("/search")
    public List<SearchResult> getFoundElements(String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket showBasket() {
        return basketService.getUserBasket();
    }
}