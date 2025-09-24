package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Map;
import java.util.UUID;

public class StorageServiceTest {

    public StorageService storageServiceTest = new StorageService();

    @Test
    public void fillProductsAndArticlesTest() {
        Map<UUID, Searchable> testArticles = storageServiceTest.getAllElements();
        Assertions.assertEquals(11, testArticles.size());
    }
}