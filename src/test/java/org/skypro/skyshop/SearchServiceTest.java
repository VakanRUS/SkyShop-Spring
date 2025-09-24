package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class SearchServiceTest {

    private StorageService storageService = Mockito.mock(StorageService.class);

    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyStorage_whenSearch_thenReturnEmptyList() {
        Mockito.when(storageService.getAllElements()).thenReturn(Collections.emptyMap());

        List<SearchResult> result = searchService.search("");

        verify(storageService, Mockito.only()).getAllElements();

        Assertions.assertEquals(true, result.isEmpty());
    }

    @Test
    public void givenStorageNotEmpty_whenSearchedElementNotFound_thenReturnEmptyList() {
        Map<UUID, Searchable> searchElements = fillTestProducts();

        Mockito.when(storageService.getAllElements()).thenReturn(searchElements);

        List<SearchResult> result = searchService.search("Носки");

        Assertions.assertEquals(true, result.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Хлеб", "хлеб"})
    public void givenStorageNotEmpty_whenSearchedElementFound_thenReturnElement(String searchTerm) {
        Map<UUID, Searchable> searchElements = fillTestProducts();

        Mockito.when(storageService.getAllElements()).thenReturn(searchElements);

        List<SearchResult> result = searchService.search(searchTerm);

        Assertions.assertEquals(true, result.get(0).getName().contains("Хлеб"));
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