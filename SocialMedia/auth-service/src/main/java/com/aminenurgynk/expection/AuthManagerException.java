package com.aminenurgynk.expection;

import lombok.Getter;

@Getter
public class AuthManagerException extends RuntimeException {

    private final ErrorType errorType;

    public AuthManagerException(ErrorType errorType) {
        this.errorType = errorType;
    }

}
