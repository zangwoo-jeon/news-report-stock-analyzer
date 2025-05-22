package com.newstoss.global.response;

/**
 * 에러 응답 메세지에 대한 DTO 클래스이다.<br>
 * 에러 Handler 에서 사용된다.
 * @Author Hyeongjun
 * @param errorCode
 * @param errorMessage
 */
public record ErrorResponse(String errorCode, String errorMessage) {
}
