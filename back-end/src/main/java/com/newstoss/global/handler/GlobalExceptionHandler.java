package com.newstoss.global.handler;

import com.newstoss.global.exception.InvalidStockPriceFormatException;
import com.newstoss.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("RUNTIME_ERROR", "알 수 없는 오류가 발생했습니다."));
    }

    @ExceptionHandler(InvalidStockPriceFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStockPriceFormatException(InvalidStockPriceFormatException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_STOCK_PRICE_FORMAT", e.getMessage()));
    }
}
