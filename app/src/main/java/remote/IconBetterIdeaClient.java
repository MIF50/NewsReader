package remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class IconBetterIdeaClient {
    private static Retrofit retrofit=null;
    // this method to get data from website as json object and return it take url of icon-better-idea
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://icons.better-idea.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
