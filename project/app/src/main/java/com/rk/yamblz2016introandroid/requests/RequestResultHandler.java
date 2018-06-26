package com.rk.yamblz2016introandroid.requests;

public  interface RequestResultHandler {
    void onResponse();
    void onErrorResponse(Exception error);
}