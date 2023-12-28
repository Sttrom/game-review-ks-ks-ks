package com.ks.gr.commons.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler (value = Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String handleException(Exception exception){
        log.error(Arrays.toString(exception.getStackTrace())); //FIXME
        return exception.getMessage();
    }
}
