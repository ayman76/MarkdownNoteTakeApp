package org.example.markdownnotetakeapp.exception;

public class EmptyFileException extends RuntimeException {
    
    public EmptyFileException(String message){
        super(message);
    }
}
