package com.rk.yamblz2016introandroid;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

import com.rk.yamblz2016introandroid.requests.*;

public class ArtistsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String url = getString(R.string.artists_url);
        final RequestMaker rm = new RequestMaker(this);

        rm.makeRequest(url, new RequestResultHandler() {
            @Override
            public void onResponse() {
            }

            @Override
            public void onErrorResponse(Exception error) {
            }
        });
    }
}
