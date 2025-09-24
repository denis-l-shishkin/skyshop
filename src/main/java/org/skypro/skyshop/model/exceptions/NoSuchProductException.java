package org.skypro.skyshop.model.exceptions;

public class NoSuchProductException extends RuntimeException {
    private final String code;

    public NoSuchProductException() {
        super("Такой товар не существует!");
        this.code = "NO_SUCH_PRODUCT";
    }

    public NoSuchProductException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}