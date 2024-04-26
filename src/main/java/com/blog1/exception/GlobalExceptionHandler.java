package com.blog1.exception;

import com.blog1.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    This exception (which is in PostServiceImpl.java) is being caught here in  GlobalExceptionHandler.java:
//    see inside ExceptionHandler annotation
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> ResourceNotFoundException(
            Exception exception,
            WebRequest webRequest
    ) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

//webrequest will give us the detail like where the exception occured
//Any exception that occurs in the project will come here


