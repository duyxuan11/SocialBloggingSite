package org.example.socialbloggingsite.exceptions.custom;

public class CustomRunTimeException extends RuntimeException{
    private ErrorCode errorCode;
    public CustomRunTimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
