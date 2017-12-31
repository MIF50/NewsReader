package model;

import java.util.List;

/**
 * {
     * "status":"ok",
     * "sources":[
         * {
             * "id":"abc-news-au",
             * "name":"ABC News (AU)",
             * "description":"Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.",
             * "url":"http://www.abc.net.au/news",
             * "category":"general",
             * "language":"en",
             * "country":"au",
             * "urlsToLogos":{"small":"","medium":"","large":""},
             * "sortBysAvailable":["top"]
         * },
         * {
             * "id":"al-jazeera-english",
             * "name":"Al Jazeera English",
             * "description":"News, analysis from the Middle East and worldwide, multimedia and interactives, opinions, documentaries, podcasts, long reads and broadcast schedule.",
             * "url":"http://www.aljazeera.com",
             * "category":"general",
             * "language":"en",
             * "country":"us",
             * "urlsToLogos":{"small":"","medium":"","large":""},
             * "sortBysAvailable":["top"]
 * }
 }
 */
// Model class that deal with json Object and this as example in top of class


public class Website {
    private String status;
    private List<Source> sources;

    public Website() {
    }

    public Website(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
