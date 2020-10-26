package com.projects.TopNewsApi.Models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ArticleDeserializer extends StdDeserializer<Article> {

    public ArticleDeserializer() {
        this(null);
    }

    protected ArticleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Article deserialize(JsonParser p, DeserializationContext ctxt) throws HttpServerErrorException {
        Article article = new Article();
        ObjectCodec codec = p.getCodec();
        JsonNode node;
        try {
            node = codec.readTree(p);
        }
        catch (IOException ex) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred while attempting to deserialize the response.");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //We deserialize the id and name into our article object to avoid having a small
        // "sources" object containing 2 fields.
        article.sourceId = node.get("source").get("id").asText();
        article.sourceName = node.get("source").get("name").asText();
        article.author = node.get("author").asText();
        article.title = node.get("title").asText();
        article.url = node.get("url").asText();
        article.urlToImage = node.get("urlToImage").asText();
        article.content = node.get("content").asText();
        try {
            article.publishedAt = format.parse(node.get("publishedAt").asText());
        } catch (ParseException ex) {
            article.publishedAt = null;
        }

        return article;
    }
}
