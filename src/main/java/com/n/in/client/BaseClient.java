package com.n.in.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

 abstract class BaseHttpClient {

    protected final HttpClient httpClient = HttpClient.newHttpClient();
    protected final ObjectMapper mapper = new ObjectMapper();

    protected <T> T sendRequest(HttpRequest request, Class<T> responseType) {
        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "HTTP error: " + response.statusCode() + " → " + response.body()
                );
            }

            return mapper.readValue(response.body(), responseType);

        } catch (Exception e) {
            throw new RuntimeException("HTTP request failed: " + e.getMessage(), e);
        }
    }
}
