package com.rk.yamblz2016introandroid.requests;

import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rk.yamblz2016introandroid.models.*;

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
                        Type listType = new TypeToken<ArrayList<Artist>>(){}.getType();
                        Artists.List = new Gson().fromJson(response, listType);
                        handler.onResponse();
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
