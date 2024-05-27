package com.example.java_qlks.service;

import com.example.java_qlks.model.NewsItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class NewsService {

    private final ResourceLoader resourceLoader;

    public NewsService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<NewsItem> readNewsItems() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = resourceLoader.getResource("classpath:Files/news.json").getInputStream();
        NewsItem[] newsItems = objectMapper.readValue(inputStream, NewsItem[].class);
        return List.of(newsItems);
    }
}

