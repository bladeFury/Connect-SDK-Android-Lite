package com.connectsdk.service.airplay.auth;

import java.io.IOException;

/**
 * Created by zhangge on 2017/6/29.
 */

public class HttpException extends IOException {
    private int code;
    private String message;

    public HttpException(int code, String msg) {
        super("http exception");
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public String getResponseMessage() {
        return message;
    }
}
