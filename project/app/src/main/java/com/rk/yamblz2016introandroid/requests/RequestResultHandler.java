package com.rk.yamblz2016introandroid.requests;

public  interface RequestResultHandler {
    void onResponse(String response);
    void onErrorResponse(Exception error);
}