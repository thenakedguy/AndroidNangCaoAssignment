package com.hngdngcorp.hngdng.androidnangcaoassignment.Model;

import android.media.Image;

public class News {
    public String title;
    public String pubDate;
    public String description;
    public String link;
    public Image thumbnail;

    public News(String title, String pubDate, String description, String link, Image thumbnail) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.link = link;
        this.thumbnail = thumbnail;
    }

    public News() {
    }
}
