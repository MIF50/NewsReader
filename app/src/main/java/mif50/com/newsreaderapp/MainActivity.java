package mif50.com.newsreaderapp;


import android.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import adpater.ListSourceAdapter;
import common.Common;
import dmax.dialog.SpotsDialog;
import inter.NewsService;
import io.paperdb.Paper;
import model.Website;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // init view in activity_main.xml
    RecyclerView recycler_service;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    AlertDialog dialog;
    //init service of Retrofit
    NewsService mService;
    // adapter
    ListSourceAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init cache
        Paper.init(this);
        // find View in activity_main.xml
        recycler_service=findViewById(R.id.recycler_service);
        recycler_service.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_service.setLayoutManager(layoutManager);

        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });
        // create service
        mService= Common.getNewsService();
        dialog=new SpotsDialog(this); // show dialog dots
        loadWebsiteSource(false);
    }

    private void loadWebsiteSource(boolean isRefresh) {
        if (!isRefresh){ // if = false
            String cache=Paper.book().read("cache");
            if (cache!=null && !cache.isEmpty()){ // if we have cache
                // convert cache from json to object
                Website website=new Gson().fromJson(cache,Website.class);
                adapter=new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                recycler_service.setAdapter(adapter);
            }
            else{ // if we not have cache
                dialog.show();
                // fetch new data
                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {
                        adapter=new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        recycler_service.setAdapter(adapter);
                        // save to cache
                        Paper.book().write("cache",new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });
            }
        }
        else {
            dialog.show();
            // fetch new data
            mService.getSources().enqueue(new Callback<Website>() {
                @Override
                public void onResponse(Call<Website> call, Response<Website> response) {
                    adapter=new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    recycler_service.setAdapter(adapter);
                    // save to cache
                    Paper.book().write("cache",new Gson().toJson(response.body()));
                    // dismiss refresh progress
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<Website> call, Throwable t) {

                }
            });
        }
    }
}
