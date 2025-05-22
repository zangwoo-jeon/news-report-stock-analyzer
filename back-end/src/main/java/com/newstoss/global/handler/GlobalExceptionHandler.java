package com.newstoss.global.handler;

import com.newstoss.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseErrorEntity> handleCustomException(CustomException e) {
        return ResponseErrorEntity.toResponseEntity(e.errorCode);
    }

    @ExceptionHandler(InvalidStockPriceFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStockPriceFormatException(InvalidStockPriceFormatException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_STOCK_PRICE_FORMAT", e.getMessage()));
    }
}
