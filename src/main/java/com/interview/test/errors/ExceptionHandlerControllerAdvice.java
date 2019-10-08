package com.interview.test.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  Chandra Bhushan Verma
 * Contoller adivce implementation for custom handling of REST API return code with HTTP status code
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(PhotoNotFoundExceptions.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public @ResponseBody
    PhotoExceptionResponse handleResourceNotFound(final PhotoNotFoundExceptions exception,
                                                  final HttpServletRequest request) {

        PhotoExceptionResponse error = new PhotoExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    PhotoExceptionResponse handleException(final Exception exception,
                                           final HttpServletRequest request) {

        PhotoExceptionResponse error = new PhotoExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    PhotoExceptionResponse handleIllegalArgumentException(final IllegalArgumentException exception,
                                                          final HttpServletRequest request) {

        PhotoExceptionResponse error = new PhotoExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());

        return error;
    }

}
