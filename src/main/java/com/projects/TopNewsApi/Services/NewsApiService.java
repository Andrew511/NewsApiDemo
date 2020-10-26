package com.projects.TopNewsApi.Services;

import com.projects.TopNewsApi.DataAccess.TopHeadlinesRequest;
import com.projects.TopNewsApi.DataAccess.TopHeadlinesRequestBuilder;
import com.projects.TopNewsApi.Models.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsApiService {
    public Map<String, Long> getDistinctArticlesBySourceOnTopic(String topic) throws HttpClientErrorException, HttpServerErrorException {
        TopHeadlinesRequest request = new TopHeadlinesRequestBuilder()
                .topic(topic)
                .buildRequest();
        List<Article> articles = request.getTopHeadlines();
        return getNumArticlesBySource(articles);
    }



    public Map<String, Long> getNumArticlesBySource(List<Article> articles) {
        //get a map of the articles source name (as ID can frequently be null) and the number of articles associated
        Map<String, Long> articlesPerSource = articles
                                                .stream()
                                                .collect(
                                                        Collectors.groupingBy(
                                                                Article::getSourceName,
                                                                Collectors.counting()));

        return articlesPerSource;
    }
}