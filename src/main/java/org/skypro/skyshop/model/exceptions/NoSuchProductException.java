package org.skypro.skyshop.model.exceptions;

public class NoSuchProductException extends RuntimeException{
    private final String message = "Такой товар не существует!";
    private final String code = "NO_SUCH_PRODUCT";
    public NoSuchProductException(){
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
