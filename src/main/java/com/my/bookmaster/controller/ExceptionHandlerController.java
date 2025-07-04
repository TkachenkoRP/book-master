package com.my.bookmaster.controller;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.exception.RefreshTokenException;
import com.my.bookmaster.model.dto.ErrorResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto entityNotFound(EntityNotFoundException e) {
        return new ErrorResponseDto(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto notValid(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String errorMessage = String.join(";", errorMessages);

        return new ErrorResponseDto(errorMessage);
    }

    @ExceptionHandler(RefreshTokenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponseDto refreshTokenException(RefreshTokenException e) {
        return new ErrorResponseDto(e.getLocalizedMessage());
    }
}
