package com.projects.TopNewsApi.DataAccess;

import java.util.*;

import com.projects.TopNewsApi.Models.Article;
import com.projects.TopNewsApi.Models.TopHeadlinesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;

@Repository
public class TopHeadlinesRequest {
    private String _country;
    private String _source;
    private String _category;
    private String _topic;
    private static final String API_BASE_URL = "https://newsapi.org/v2/top-headlines?";
    //dont store your api key in a public repo
    @Value("${API_KEY}")
    private static String api_key;

    public TopHeadlinesRequest() {

    }

    public TopHeadlinesRequest(String country, String source, String category, String topic) {
        this._category = category;
        this._country = country;
        this._source = source;
        this._topic = topic;
    }

    public List<Article> getTopHeadlines() throws HttpClientErrorException, HttpServerErrorException {
        String uri = this.buildRequestUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TopHeadlinesResponse> response;
        response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<TopHeadlinesResponse>() {});

        return response.getBody().getArticles();
    }

    private String buildRequestUri() {
        String uriBuilder = API_BASE_URL;

        if (!_country.equals("")) {
            uriBuilder += "country=" + _country + "&";
        }
        if (!_source.equals("")) {
            uriBuilder += "sources=" + _source + "&";
        }
        if (!_category.equals("")) {
            uriBuilder += "category=" + _category +"&";
        }
        if (!_topic.equals("")) {
            uriBuilder += "q=" + _topic + "&";
        }

        uriBuilder += "apiKey=" + api_key;
        return uriBuilder;
    }


}