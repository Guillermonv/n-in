package com.n.in.client;

import com.n.in.dto.unplash.UnsplashSearchResponse;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class UnsplashClient extends BaseHttpClient {

    private static final String URL =
            "https://api.unsplash.com/search/photos?page=1&query=%s&client_id=&lang=es";

    public UnsplashSearchResponse searchPhotos(String query) {

        RequestStrategy strategy = (q) -> HttpRequest.newBuilder()
                .uri(URI.create(String.format(URL, q)))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpRequest request = strategy.build(query);

        return sendRequest(request, UnsplashSearchResponse.class);
    }
}
