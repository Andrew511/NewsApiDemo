package com.projects.TopNewsApi.Controllers;

import com.projects.TopNewsApi.Services.NewsApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Map;

@RestController
public class TopicController {

    @RequestMapping("/DistinctArticlesBySourceOnTopic/{topic}")
    public ResponseEntity GetDistinctArticlesBySourceOnTopic(@PathVariable String topic) {
        NewsApiService service = new NewsApiService();
        ResponseEntity response;
        try {
            Map<String, Long> articlesPerSource = service.getDistinctArticlesBySourceOnTopic(topic);
            response = new ResponseEntity(articlesPerSource, HttpStatus.OK);
        }
        catch (HttpClientErrorException | HttpServerErrorException ex) {
            response = new ResponseEntity(ex.getMessage(), ex.getStatusCode());
        }

        return response;
    }
}