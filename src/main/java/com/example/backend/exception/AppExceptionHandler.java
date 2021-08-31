package com.example.backend.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public String fileTooLargeException(){
        return "exceptions/filesizelimit";
    }
}
