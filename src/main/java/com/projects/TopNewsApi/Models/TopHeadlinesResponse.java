package com.projects.TopNewsApi.Models;

import java.util.List;


public class TopHeadlinesResponse {
    String status;
    int totalResults;
    List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
