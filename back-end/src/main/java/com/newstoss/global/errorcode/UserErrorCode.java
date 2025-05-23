package com.newstoss.global.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "USER-001", "사용자를 찾을 수 없습니다."),
    DUPLICATE_ACCOUNT(HttpStatus.BAD_REQUEST, false, "USER-002", "이미 존재하는 계정입니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;

}
