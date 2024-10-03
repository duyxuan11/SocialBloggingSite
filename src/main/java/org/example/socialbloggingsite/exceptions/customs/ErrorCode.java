package org.example.socialbloggingsite.exceptions.customs;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    DELETE_FILE_NOT_SUCCESS(400, "Delete File Not Success",HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND(404, "File Not Found",HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED(401, "Token Is Expired",HttpStatus.UNAUTHORIZED),
    USER_EXISTS(409, "Username Exists",HttpStatus.CONFLICT),
    EMAIL_EXISTS(409, "Email Exists",HttpStatus.CONFLICT),
    USER_NOT_FOUND(404, "User Not Found",HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_EXPIRED(403,"Refresh Token Has Expired",HttpStatus.FORBIDDEN),
    INVALID_TOKEN(403, "Invalid Token",HttpStatus.UNAUTHORIZED),
            ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
