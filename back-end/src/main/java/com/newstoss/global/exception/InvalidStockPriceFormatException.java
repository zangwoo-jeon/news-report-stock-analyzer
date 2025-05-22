package com.newstoss.global.exception;

public class InvalidStockPriceFormatException extends RuntimeException{
    public InvalidStockPriceFormatException() {
        super("주식 가격 형식이 잘못되었습니다.");
    }

    public InvalidStockPriceFormatException(String message) {
        super(message);
    }
    public InvalidStockPriceFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
