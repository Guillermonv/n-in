package com.n.in.provider.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public abstract class SimpleRestClient {

    protected final HttpClient httpClient = HttpClient.newHttpClient();
    protected final ObjectMapper mapper = new ObjectMapper();

    protected abstract HttpRequest buildRequest(Object input) throws Exception;

    protected abstract <T> Class<T> responseType();

    public <T> T execute(Object input) {
        try {
            HttpRequest request = buildRequest(input);

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "HTTP error: " + response.statusCode() + " → " + response.body()
                );
            }

            return mapper.readValue(response.body(), responseType());

        } catch (Exception e) {
            throw new RuntimeException("HTTP request failed: " + e.getMessage(), e);
        }
    }
}
