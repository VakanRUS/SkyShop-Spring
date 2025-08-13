package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        fillProductsAndArticles();
    }

    public void fillProductsAndArticles() {
        FixPriceProduct product1 = new FixPriceProduct(UUID.randomUUID(), "Яблоки");
        DiscountedProduct product2 = new DiscountedProduct(UUID.randomUUID(), "Картошка", 60, 15);
        FixPriceProduct product3 = new FixPriceProduct(UUID.randomUUID(), "Молоко");
        DiscountedProduct product4 = new DiscountedProduct(UUID.randomUUID(), "Хлеб \"Бородинский\"", 40, 15);
        SimpleProduct product5 = new SimpleProduct(UUID.randomUUID(), "Сок \"Мультифрукт\"", 240);
        DiscountedProduct product6 = new DiscountedProduct(UUID.randomUUID(), "Масло", 200, 30);
        FixPriceProduct product7 = new FixPriceProduct(UUID.randomUUID(), "Хлеб \"Сендвичный\"");
        SimpleProduct product8 = new SimpleProduct(UUID.randomUUID(), "Вишня", 100);

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
        products.put(product4.getId(), product4);
        products.put(product5.getId(), product5);
        products.put(product6.getId(), product6);
        products.put(product7.getId(), product7);
        products.put(product8.getId(), product8);

        Article article1 = new Article(UUID.randomUUID(), "Кефира побольше!", "Кефир " + "Колбаса " + "Кефир " + "Батон " + "Кефир ");
        Article article2 = new Article(UUID.randomUUID(), "Типичный обед студента.", "Кефир " + "Сайка ");
        Article article3 = new Article(UUID.randomUUID(), "Рецепт окрошки на кефире.", "Кефир " + "Хлеб " + "Колбаса " + "Яйцо ");

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
    }

    public Map<UUID, Product> getProducts() {
        return this.products;
    }

    public Map<UUID, Article> getArticles() {
        return this.articles;
    }

    public Map<UUID, Searchable> getAllElements() {
        Map<UUID, Searchable> mapAllElements = new HashMap<>();
        mapAllElements.putAll(products);
        mapAllElements.putAll(articles);
        return mapAllElements;
    }
}
