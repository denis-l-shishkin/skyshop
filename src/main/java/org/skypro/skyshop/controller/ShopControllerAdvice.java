package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException e) {
        ShopError shopError = new ShopError(e.getCode(), e.getMessage());
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
