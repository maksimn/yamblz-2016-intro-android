package com.rk.yamblz2016introandroid.requests;

public  interface RequestResultHandler {
    void onResponse(String result);
    void onErrorResponse(Exception error);
}