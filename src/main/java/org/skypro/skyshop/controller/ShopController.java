package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService) {
        this.storageService = storageService;
        this.searchService = new SearchService(storageService);
    }

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts(){
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getAllArticles(){
        return storageService.getArticles();
    }

    @GetMapping("/all")
    public Map<UUID, Searchable> getAllElements(){
        return storageService.getAllElements();
    }

    @GetMapping("/search")
    public List<SearchResult> getFoundElements( String pattern){
        return searchService.search(pattern);
    }
}
