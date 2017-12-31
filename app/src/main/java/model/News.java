package model;

import java.util.List;



/**
 * {
     * "status":"ok",
     * "source":"the-next-web",
     * "sortBy":"top",
     * "articles":[
         * {
         * "author":"Abhimanyu Ghoshal","
         * title":"Tidal might die in 2018,leaving audiophiles in the lurch",
         * "description":"Norwegian outlet Dagens Næringsliv reports that Tidal now only has enough money left to last another six months in the competitive streaming music business - which is bad news for ...",
         * "url":"https://thenextweb.com/apps/2017/12/13/tidal-might-die-in-2018-leaving-audiophiles-in-the-lurch/",
         * "urlToImage":"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/12/Tidal-hed-social.jpg",
         * "publishedAt":"2017-12-13T07:13:06Z"
         * },
         * {
         * "author":"Abhimanyu Ghoshal",
         * "title":"Google is building its next AI research center in China",
         * "description":"Google announced today that it's first artificial intelligence research center in Asia will be set up in China, underlining the country's dominance in the fast-growing field.\r\n\r\nThe ...",
         * "url":"https://thenextweb.com/artificial-intelligence/2017/12/13/google-is-building-its-next-ai-research-center-in-china/",
         * "urlToImage":"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/12/Google-hed-social.jpg",
         * "publishedAt":"2017-12-13T06:23:17Z"
         * }
 * ]
 * }
 */

// this class model deal with json object like example abave

public class News {
    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public News() {
    }

    public News(String status, String source, String sortBy, List<Article> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

