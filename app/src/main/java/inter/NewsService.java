package inter;

import model.News;
import model.Website;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * Created by mohamed on 12/10/17.
 */

public interface NewsService {
    // this interface use to fetch data from json as Object of Website (status:String,Sources:List)
    // used this url in @GET to get data
    @GET("v1/sources?language=en")
    Call<Website> getSources();

    // get get data from web site (json)  as News Object
    @GET
    Call<News> getNewestArticle(@Url String url);
}
