package org.skypro.skyshop.controller;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ShopError noSuchProductException(NoSuchProductException e) {
        return new ShopError("404", "нет продукта с таким id");
    }
}