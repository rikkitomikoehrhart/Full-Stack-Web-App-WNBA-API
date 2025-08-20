package com.rikkitomikoehrhart.league_standard.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rikkitomikoehrhart.league_standard.data.NewsRepo;
import com.rikkitomikoehrhart.league_standard.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsDataService {
    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.base.url}")
    private String baseUrl;

    @Autowired
    private NewsRepo newsRepo;

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public NewsDataService() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
        this.objectMapper = new ObjectMapper();
    }

    public void loadNewsData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", "wnba-api.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                processNews(response.body());
            } else {
                System.err.print("News API request failed with status: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error fetching news data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processNews(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);

            News news = new News();

            JsonNode descriptionNode = root.get("description");
            news.setDescription(descriptionNode != null ? descriptionNode.asText() : "");

            JsonNode headlineNode = root.get("headline");
            news.setHeadline(headlineNode != null ? headlineNode.asText() : "");


            JsonNode imagesNode = root.get("images");
            List<String> imagesArray = new ArrayList<>();

            if (imagesNode != null && imagesNode.isArray()) {
                for (JsonNode imageNode : imagesNode) {
                    imagesArray.add(imageNode.asText());
                }
            }

            news.setImage_urls(imagesArray);

            JsonNode linkNode = root.get("link");
            news.setLink(linkNode != null ? linkNode.asText() : "");

            JsonNode publishedNode = root.get("published");
            if (publishedNode != null) {
                try {
                    news.setPublished(LocalDateTime.parse(publishedNode.asText()));
                } catch (DateTimeException e) {
                    System.err.print("Error parsing published date: " + e.getMessage());
                }
            }


            List<Map<String, String>> categoriesHashMapList = new ArrayList<>();
            JsonNode categoriesNode = root.get("categories");

            if (categoriesNode != null && categoriesNode.isArray()) {
                for (JsonNode categoryNode : categoriesNode) {
                    Map<String, String> newCategory = new HashMap<>();

                    JsonNode catDescriptionNode = categoryNode.get("description");
                    newCategory.put("description", catDescriptionNode != null ? catDescriptionNode.asText() : "");

                    JsonNode catTypeNode = categoryNode.get("type");
                    newCategory.put("type", catTypeNode != null ? catTypeNode.asText() : "");

                    categoriesHashMapList.add(newCategory);
                }
            }
            news.setCategories(categoriesHashMapList);

            newsRepo.addNews(news);
        } catch (Exception e) {
            System.err.println("Error processing news: " + e.getMessage());
        }
    }
}
