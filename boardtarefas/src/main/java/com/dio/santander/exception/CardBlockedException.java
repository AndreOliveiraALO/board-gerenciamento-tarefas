package com.dio.santander.exception;

public class CardBlockedException extends RuntimeException{

    public CardBlockedException(final String message) {
        super(message);
    }
}
