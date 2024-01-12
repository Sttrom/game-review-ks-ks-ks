package com.ks.gr.commons.exception.handler;

import com.ks.gr.commons.entity.dto.BaseExceptionResponseDto;
import com.ks.gr.commons.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseExceptionResponseDto badMethodArguments(MethodArgumentTypeMismatchException exception) {
        log.info("Bad method arguments: ", exception);
        return new BaseExceptionResponseDto("Bad method arguments: " + exception.getValue());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseExceptionResponseDto httpUnreadable(HttpMessageNotReadableException exception) {
        log.info("Request body is unreadable: ", exception);
        return new BaseExceptionResponseDto("Exception reading request body, invalid JSON");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseExceptionResponseDto entityNotFound(EntityNotFoundException exception) {
        log.info("Entity not found: ", exception);
        return new BaseExceptionResponseDto(exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseExceptionResponseDto unexpectedException(Exception exception) {
        log.error("Unexpected exception: ", exception);
        return new BaseExceptionResponseDto("Unexpected exception");
    }
}
