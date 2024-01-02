package com.ks.gr.commons.exception.handler;

import com.ks.gr.commons.dto.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public BaseErrorResponse handleException(Exception exception) { //FIXME
        log.error("Error", exception);
        return new BaseErrorResponse(exception.getMessage());
    }
}
