package com.demo.api.postcode.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.demo.api.postcode.entity.APIStatus;

import java.util.Set;
import org.springframework.http.HttpStatus;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Global Exception Handler to handle Rest API Exceptions
 * 
 * 
 * @author kovitadjanlakhon
 *
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIStatus handleBadRequestException(ConstraintViolationException e) {
         Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
         StringBuilder strBuilder = new StringBuilder();
         for (ConstraintViolation<?> violation : violations ) {
              strBuilder.append(violation.getMessage() + "\n");
         }
         return new APIStatus("API-400", strBuilder.toString());
    }
	
	@ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIStatus resourceNotFoundException(Exception ex, WebRequest req) {
        return new APIStatus("API-404", "Resource Not Found");
    }
	
	@ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIStatus unknownException(Exception ex, WebRequest req) {
        return new APIStatus("API-500", "Server Error");
    }

}
