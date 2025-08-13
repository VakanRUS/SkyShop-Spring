package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {

    @JsonIgnore
    String getSearchTerm();

    String getName();

    @JsonIgnore
    String getContentType();

    UUID getId();

    default String getStringRepresentation() {
        return getName() + " " + getContentType() + "\n";
    }
}
