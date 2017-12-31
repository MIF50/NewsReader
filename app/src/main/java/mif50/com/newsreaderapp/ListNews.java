package mif50.com.newsreaderapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import adpater.ListNewsAdapter;
import common.Common;
import dmax.dialog.SpotsDialog;
import inter.NewsService;
import model.Article;
import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {
    private static final String SOURCE="source";
    private static final String SORTBY="sortby";
    private String source="",sort_by="";
    // init view in actvity_list_news
    DiagonalLayout diagonalLayout;
    KenBurnsView kbv;
    TextView article_author,article_title;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recycler_list;
    RecyclerView.LayoutManager layoutManager;
    // adapter
    ListNewsAdapter adapter;
    AlertDialog dialog;
    // int service of Retrofit
    NewsService mService;

    String webHotUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        // create service
        mService=Common.getNewsService();
        // init View in activity_list_news.xml
        diagonalLayout=findViewById(R.id.diagonal);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DetailsArticle.newIntent(getBaseContext(),webHotUrl)); // move to DetailsArticle and send url
            }
        });
        kbv=findViewById(R.id.top_image);
        article_author=findViewById(R.id.article_author);
        article_title=findViewById(R.id.article_title);
        recycler_list=findViewById(R.id.recycler_list);
        recycler_list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_list.setLayoutManager(layoutManager);
        swipeRefreshLayout=findViewById(R.id.swipe_news_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source,true); // load data and adapt it in RecyclerView
            }
        });
        dialog=new SpotsDialog(this);
        // get source and sortBy from intent
        if (getIntent()!=null){
            source=getIntent().getStringExtra(SOURCE);
            sort_by=getIntent().getStringExtra(SORTBY);
            if (!source.isEmpty()){
                loadNews(source,false);
            }
        }

        
    }

    /* this method that load data in RecyclerView */
    private void loadNews(String source, boolean isRefresh) {
        String url = Common.getApiUrl(source,sort_by,Common.Api_key); // get url of json data
        Call<News> call=mService.getNewestArticle(url); // get data as Call in News Model
        if (!isRefresh){// data is not load we have not refreshed
            dialog.show();

            call.enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            // response => contain data as News Model
                            dialog.dismiss();
                            Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kbv);
                            article_author.setText(response.body().getArticles().get(0).getAuthor());
                            article_title.setText(response.body().getArticles().get(0).getTitle());
                            webHotUrl=response.body().getArticles().get(0).getUrl(); // url of link in internet
                            // load remain Articles
                            List<Article> removeFirst=response.body().getArticles();
                            // because we load first item in diagonal we remove first from list
                            removeFirst.remove(0);
                            // load data in recycler
                            adapter=new ListNewsAdapter(getBaseContext(),removeFirst);
                            adapter.notifyDataSetChanged(); // to load data if refresh
                            recycler_list.setAdapter(adapter); // set up adapter
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
        }
        else {
            dialog.show();
            call.enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            // response => contain data as News Model
                            dialog.dismiss();
                            Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kbv);
                            article_author.setText(response.body().getArticles().get(0).getAuthor());
                            article_title.setText(response.body().getArticles().get(0).getTitle());
                            webHotUrl=response.body().getArticles().get(0).getUrl();
                            // load remain Articles
                            List<Article> removeFirst=response.body().getArticles();
                            // because we load first item in diagonal we remove first from list
                            removeFirst.remove(0);
                            // load data in recycler
                            adapter=new ListNewsAdapter(getBaseContext(),removeFirst);
                            adapter.notifyDataSetChanged(); // to load data if refresh
                            recycler_list.setAdapter(adapter); // set up adapter
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    /*
       this method used to move to this Activity ListNews
     * @param context-> activity that come from , source->value that send , sortBy->value that send
     * @return intent
     */
    public static Intent newIntent(Context context,String source,String sortBy){
        Intent intent =new Intent(context,ListNews.class);
        intent.putExtra(SOURCE,source);
        intent.putExtra(SORTBY,sortBy);
        return intent;
    }
}
