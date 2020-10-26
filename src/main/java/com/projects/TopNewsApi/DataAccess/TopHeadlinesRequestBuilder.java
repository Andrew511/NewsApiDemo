package com.projects.TopNewsApi.DataAccess;

import org.springframework.stereotype.Repository;

@Repository
public class TopHeadlinesRequestBuilder {
    private String _country = "";
    private String _source = "";
    private String _category = "";
    private String _topic = "";

    public TopHeadlinesRequestBuilder() { }
    //we use a builder pattern here to allow for default parameters and to allow the request class
    // to handle all permutations of possible querys without a large amount of overload methods.
    public TopHeadlinesRequest buildRequest() {
        return new TopHeadlinesRequest(_country, _source, _category, _topic);
    }

    public TopHeadlinesRequestBuilder country(String country) {
        this._country = country;
        return this;
    }

    public TopHeadlinesRequestBuilder source(String source) {
        this._source = source;
        return this;
    }

    public TopHeadlinesRequestBuilder category(String category) {
        this._category = category;
        return this;
    }

    public TopHeadlinesRequestBuilder topic(String topic) {
        this._topic = topic;
        return this;
    }
}