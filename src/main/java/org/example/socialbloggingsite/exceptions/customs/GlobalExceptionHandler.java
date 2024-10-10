package org.example.socialbloggingsite.exceptions.customs;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception e) {
        ProblemDetail problemDetail = null;

        e.printStackTrace();
        if (e instanceof BadCredentialsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            problemDetail.setProperty("description", "The Username or password is not correct!");
            return problemDetail;
        }
        if (e instanceof AccountStatusException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
            problemDetail.setProperty("description", "The Account is locked!");
        }
        if (e instanceof AccessDeniedException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
            problemDetail.setProperty("description", "You are not authorized to access this resource!");
            return problemDetail;
        }
        if (e instanceof ExpiredJwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            problemDetail.setProperty("description", "The token has expired!");
            return problemDetail;
        }
        if (e instanceof MethodArgumentNotValidException) {
            String errorDescription = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), errorDescription);
        }
        if (problemDetail == null) {
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
            problemDetail.setProperty("description", e.getMessage());
            return problemDetail;
        }
        return problemDetail;
    }

    @ExceptionHandler(value = CustomRunTimeException.class)
    public ProblemDetail handleCusTomRuntimeException(CustomRunTimeException e) {
        ErrorCode errorCode = e.getErrorCode();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(errorCode.getCode()), e.getMessage());
        problemDetail.setProperty("description", e.getMessage());
        return problemDetail;
    }
}
