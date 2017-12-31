package inter;

import model.IconBetterIdea;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mohamed on 12/11/17.
 */

public interface IconBetterIdeaService {
    // this interface used to fetch data that related to icon that will show return data As IconBetterIdea (url:String,Icon:list)
    @GET
    Call<IconBetterIdea> getIconUrl(@Url String url);
}
