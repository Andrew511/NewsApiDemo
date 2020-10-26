package com.projects.TopNewsApi.DataAccess;

import org.junit.jupiter.api.Test;

public class TopHeadlinesRequestTests {
    private final String COUNTRY = "country=";
    private final String TOPIC = "q=";
    private final String CAT = "category=";
    private final String SOURCE = "sources=";

    @Test
    public void RequestBuilderUriTest() {
        String topic = "param1";
        String country = "param2";
        String cat = "param3";
        String source = "param4";

        TopHeadlinesRequest request = new TopHeadlinesRequestBuilder()
                                                .topic(topic)
                                                .category(cat)
                                                .source(source)
                                                .country(country)
                                                .buildRequest();
        assert request != null;
    }
}
