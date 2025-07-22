package org.example.markdownnotetakeapp.exception;

import org.example.markdownnotetakeapp.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(InvalidExtensionException.class)
    public ResponseEntity<Error> handleInvaludeExtensionException(InvalidExtensionException invalidExtensionException){
        return ResponseEntity.status(500).body(
            new Error().builder()
                .message(invalidExtensionException.getMessage())
                .code(500)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build()
        );
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<Error> handleEmptyFileException(EmptyFileException emptyFileException){
        return ResponseEntity.status(400).body(new Error().builder()
                    .message(emptyFileException.getMessage())
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST).build());       
    }
}
