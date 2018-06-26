package com.rk.yamblz2016introandroid.requests;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class RequestMaker {
    private Context context;

    public RequestMaker(final Context context) {
        this.context = context;
    }

    public void makeRequest(final String url, final RequestResultHandler handler) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handler.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onErrorResponse(error);
                    }
        });

        queue.add(stringRequest);
    }
}
