package com.alp.mvp;

import com.alp.library.exception.IErrorHandler;

import javax.inject.Inject;

public class ErrorHandler implements IErrorHandler {

    @Inject
    ErrorHandler() {
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        throwable.printStackTrace();

        return "ThrowableThrowableThrowableThrowableThrowable";
    }
}