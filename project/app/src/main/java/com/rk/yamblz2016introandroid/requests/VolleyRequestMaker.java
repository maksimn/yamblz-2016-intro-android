package com.rk.yamblz2016introandroid.requests;

import java.io.UnsupportedEncodingException;
import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class VolleyRequestMaker implements RequestMaker {
    private Context context;

    public VolleyRequestMaker(final Context context) {
        this.context = context;
    }

    public void makeRequest(final String url, final RequestResultHandler handler) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result;
                        try {
                            byte bytes[] = response.getBytes("ISO-8859-1");
                            result = new String(bytes, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            result = response;
                        }
                        handler.onResponse(result);
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
