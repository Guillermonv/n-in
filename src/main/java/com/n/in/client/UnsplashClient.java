package com.n.in.client;

import com.n.in.dto.unplash.UnsplashSearchResponse;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class UnsplashClient {

    private static final String URL =
            "https://api.unsplash.com/search/photos?page=1&query=clown&client_id=3raBUTruWk_fZV9GzBh9qgjk3xlzS-RJWE-e8IYeZZQ&lang=es";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public UnsplashClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public UnsplashSearchResponse searchPhotos(String query) throws Exception {

        String finalUrl = String.format(URL, query);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Unsplash error: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), UnsplashSearchResponse.class);
    }
}
