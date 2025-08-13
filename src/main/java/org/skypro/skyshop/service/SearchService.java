package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String searchTerm){
        List<SearchResult> searchResults = new ArrayList<>();
        Map<UUID, Searchable> elements = storageService.getAllElements();
        List<Searchable> searchElements = elements.values().stream()
                .filter(Objects::nonNull)
                .filter(el -> el.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();
        for (Searchable searchElement : searchElements) {
            searchResults.add(SearchResult.fromSearchable(searchElement));
        }
        return searchResults;
    }
}