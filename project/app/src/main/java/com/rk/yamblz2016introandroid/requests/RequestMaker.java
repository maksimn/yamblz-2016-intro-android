package com.rk.yamblz2016introandroid.requests;

public interface RequestMaker {
    void makeRequest(String url, RequestResultHandler handler);
}
