package com.tang.missu.exception.http;

public class NotFoundException extends HttpException{
    public NotFoundException(int code) {
        this.code = code;
        this.httpStatusCode = 401;
    }
}
