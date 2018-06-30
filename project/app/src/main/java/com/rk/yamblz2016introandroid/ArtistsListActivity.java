package com.rk.yamblz2016introandroid;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rk.yamblz2016introandroid.adapters.*;
import com.rk.yamblz2016introandroid.models.Artist;
import com.rk.yamblz2016introandroid.models.Artists;
import com.rk.yamblz2016introandroid.requests.*;

public class ArtistsListActivity extends AppCompatActivity implements ArtistsListAdapter.ItemClickListener {
    private ArtistsListAdapter adapter;
    private static int layoutPosition;
    private static int offset;

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
                LinearLayoutManager layoutManager =(LinearLayoutManager)recyclerView.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(layoutPosition, offset);
            }

            @Override
            public void onErrorResponse(Exception error) {
                // show error of the data request on the device screen
                Toast.makeText(self, "Ошибка получения данных приложения",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        final RecyclerView recyclerView = findViewById(R.id.artistsList);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;
        layoutPosition = linearLayoutManager.findFirstVisibleItemPosition();
        View v = layoutManager.getChildAt(0);
        offset = v.getTop();
        Intent intent = new Intent(this, ArtistDetailActivity.class);
        Artist artist = Artists.List.get(position);
        intent.putExtra("text/json", new Gson().toJson(artist));
        startActivity(intent);
    }
}
