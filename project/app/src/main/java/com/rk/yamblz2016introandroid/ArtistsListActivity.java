package com.rk.yamblz2016introandroid;

import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import com.rk.yamblz2016introandroid.adapters.*;
import com.rk.yamblz2016introandroid.models.Artists;
import com.rk.yamblz2016introandroid.requests.*;

public class ArtistsListActivity extends AppCompatActivity implements ArtistsListAdapter.ItemClickListener {
    private ArtistsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.artistsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String url = getString(R.string.artists_url);
        final RequestMaker rm = new VolleyRequestMaker(this);
        final ArtistsListActivity self = this;

        rm.makeRequest(url, new RequestResultHandler() {
            @Override
            public void onResponse() {
                // In this onResponse() callback you can get data from Artists.list
                adapter = new ArtistsListAdapter(self, Artists.List);
                adapter.setClickListener(self);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(Exception error) {
                // show error of the data request on the device screen
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
