package common;

import inter.IconBetterIdeaService;
import inter.NewsService;
import remote.IconBetterIdeaClient;
import remote.RetrofitClient;

/**
 * Created by mohamed on 12/11/17.
 */

public class Common {
    private static final String base_url="https://newsapi.org/";
    public static final String Api_key="38452b3dbdc64b5aba76dc73c70fe3d1";
    /*this method that return data as Object of NewsService (Interface) that return data as Website.class Object
    url used (base_url+@GET("v1/sources?language=en") data in this url json*/
    public static NewsService getNewsService(){
        return RetrofitClient.getClient(base_url).create(NewsService.class);
    }
    /* this method  that return data as Object of IconBetterIdeaService (Interface) that return data as IconBetterIdea.class
    * url used (https://icons.better-idea.org/ + url of icon of item in adapter od data*/
    public static IconBetterIdeaService getIconService(){
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    /* this method to prepare url that open json data that we take it to handle it as Model object News
    *@return url
     * https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=38452b3dbdc64b5aba76dc73c70fe3d1
     **/
    public static String getApiUrl(String source,String sortBy,String api_key){
        StringBuilder apiUrl=new StringBuilder("https://newsapi.org/v1/articles?source=");
        return apiUrl.append(source)
                .append("&sortBy=")
                .append(sortBy)
                .append("&apiKey=")
                .append(api_key).toString();
    }
}
