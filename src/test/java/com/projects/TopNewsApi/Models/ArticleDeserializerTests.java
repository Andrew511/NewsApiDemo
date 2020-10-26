package com.projects.TopNewsApi.Models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class ArticleDeserializerTests {
    private ObjectMapper mapper;
    private ArticleDeserializer deserializer;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new ArticleDeserializer();
    }

    @Test
    void deserializeTest() {
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            InputStream stream = loader.getResourceAsStream("ArticleTest.json");
            JsonParser parser = mapper.getFactory().createParser(stream);
            DeserializationContext ctxt = mapper.getDeserializationContext();

            Article deserialized = deserializer.deserialize(parser, ctxt);

            assert !deserialized.author.isEmpty();
            assert !deserialized.content.isEmpty();
            assert !deserialized.urlToImage.isEmpty();
            assert !deserialized.url.isEmpty();
            assert !deserialized.title.isEmpty();
            assert !deserialized.sourceName.isEmpty();
            assert !deserialized.sourceId.isEmpty();
        }
        catch ( IOException ex) {
            assert false;
        }
    }

    @Test
    public void deserializeBadJsonTest() {
        boolean caughtException = false;

        try {
            ClassLoader loader = this.getClass().getClassLoader();
            InputStream stream = loader.getResourceAsStream("BadJsonTest.json");
            JsonParser parser = mapper.getFactory().createParser(stream);
            DeserializationContext ctxt = mapper.getDeserializationContext();

            Article deserialized = deserializer.deserialize(parser, ctxt);
        }
        catch (HttpServerErrorException ex) {
            caughtException = true;
        }
        catch (IOException ex) {
            assert false;
        }

        assert caughtException;
    }
}
