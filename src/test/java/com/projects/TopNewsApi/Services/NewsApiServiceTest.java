package com.projects.TopNewsApi.Services;

import com.projects.TopNewsApi.Models.Article;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class NewsApiServiceTest {
    public void countTwoSeperateSourcesTest() {
        NewsApiService service = new NewsApiService();

        Article article1 = new Article();
        Article article2 = new Article();

        article1.sourceName = "place1";
        article2.sourceName = "place2";

        List<Article> articleList = new ArrayList<>();

        articleList.add(article1);
        articleList.add(article2);

        Map<String,Long> articles = service.getNumArticlesBySource(articleList);

        assert articles.size() == articleList.size();
        assert articles.get(article1.sourceName) == 1;
        assert articles.get(article2.sourceName) == 1;
    }

    public void countOneSourceMultiplePublicationsTest() {
        NewsApiService service = new NewsApiService();

        Article article1 = new Article();
        Article article2 = new Article();

        article1.sourceName = "place1";
        article2.sourceName = "place1";

        List<Article> articleList = new ArrayList<>();

        articleList.add(article1);
        articleList.add(article2);

        Map<String,Long> articles = service.getNumArticlesBySource(articleList);

        assert articles.size() == articleList.size();
        assert articles.get(article1.sourceName) == 2;
    }
}
