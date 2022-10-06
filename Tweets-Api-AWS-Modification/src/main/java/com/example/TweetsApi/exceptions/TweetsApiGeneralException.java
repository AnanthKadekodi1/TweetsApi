package com.example.TweetsApi.exceptions;

public class TweetsApiGeneralException extends RuntimeException{
    public TweetsApiGeneralException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    public TweetsApiGeneralException(String exMessage) {
        super(exMessage);
    }
}

