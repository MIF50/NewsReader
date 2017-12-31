package remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed on 12/10/17.
 */

public class RetrofitClient {
    private static Retrofit retrofit=null;
    // this method to get data from website as json object and return it take url of page
    public static Retrofit getClient(String baseUrl){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
