package mif50.com.newsreaderapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dmax.dialog.SpotsDialog;

public class DetailsArticle extends AppCompatActivity {
    private static final String URL="url";
    // init view in detail_article_activity.xml
    AlertDialog dialog;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);
        dialog=new SpotsDialog(this);
        // find view in detail_article_activity.xml
        web=findViewById(R.id.web_view);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        // get data from intent
        if (getIntent()!=null){
            String url_web=getIntent().getStringExtra(URL);
            if (!url_web.isEmpty() && url_web!=null){
                web.loadUrl(url_web); // one ulr in activity_detail_article.xml
            }
        }
    }
    /*this method used to move to this Activity DetailsArticel
    * @param context-> activity that that come from , url -> value send to this activity
     * @return intent
     * */
    public static Intent newIntent(Context context,String url){
        Intent intent= new Intent(context,DetailsArticle.class);
        intent.putExtra(URL,url);
        return intent;
    }
}
