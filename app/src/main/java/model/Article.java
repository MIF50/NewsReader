package model;

/**
 * {
 * "author":"Abhimanyu Ghoshal","
 * title":"Tidal might die in 2018,leaving audiophiles in the lurch",
 * "description":"Norwegian outlet Dagens Næringsliv reports that Tidal now only has enough money left to last another six months in the competitive streaming music business - which is bad news for ...",
 * "url":"https://thenextweb.com/apps/2017/12/13/tidal-might-die-in-2018-leaving-audiophiles-in-the-lurch/",
 * "urlToImage":"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/12/Tidal-hed-social.jpg",
 * "publishedAt":"2017-12-13T07:13:06Z"
 * }
 */
// this class model deal what Article Json Object contain
public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article() {
    }

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
