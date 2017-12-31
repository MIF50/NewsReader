package model;

import java.util.List;

/**
 * {
 * "url":"http://www.abc.net.au/news",
 * "icons":[
     * {
     * "url":"http://mobile.abc.net.au/homepage/mobile/images/homepage/apple-touch-icon-144x144.png",
     * "width":144,
     * "height":144,
     * "format":"png",
     * "bytes":22489,
     * "error":null,
     * "sha1sum":"da9aa590516da911a9581cc8bf4b7520c8f54371"
     * },
     * {
     * "url":"http://mobile.abc.net.au/homepage/mobile/images/homepage/apple-touch-icon-114x114.png",
     * "width":114,
     * "height":114,
     * "format":"png",
     * "bytes":15866,
     * "error":null,
     * "sha1sum":"59f2a56918c6b189d9a48d202d898811165a54c9"
     * }
 * ]
 * }
 */
// this Model class deal with json icons

public class IconBetterIdea {
    private String url;
    private List<Icon> icons;

    public IconBetterIdea() {
    }

    public IconBetterIdea(String url, List<Icon> icons) {
        this.url = url;
        this.icons = icons;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
