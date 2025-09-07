package org.skypro.skyshop.controller;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.exceptions.ShopError;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException e) {
        ShopError shopError = new ShopError("NO_SUCH_PRODUCT_WITH_THIS_ID", "Продукт с таким id не найден");
        return new ResponseEntity<>(shopError, HttpStatusCode.valueOf(404));
    }
}