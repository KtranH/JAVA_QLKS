package com.example.java_qlks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsItem {
    @JsonProperty("image")
    public String image;

    @JsonProperty("title")
    public String title;

    @JsonProperty("content")
    public String content;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
