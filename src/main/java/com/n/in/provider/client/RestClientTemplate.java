package com.n.in.provider.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public abstract class RestClientTemplate {

    protected final HttpClient httpClient = HttpClient.newHttpClient();
    protected final ObjectMapper mapper = new ObjectMapper();

    protected abstract HttpRequest buildRequest(Object input,String secret) throws Exception;

    protected abstract <T> Class<T> responseType();

    public <T> T execute(Object input, String secret) {
        try {
            HttpRequest request = buildRequest(input,secret);

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


    public <T> T executeWithFallback(Object input, java.util.function.Supplier<T> fallback,String secret) {
        try {
            return execute(input,secret);
        } catch (Exception e) {
            return fallback.get();
        }
    }
}
