package com.n.in.client;


import com.n.in.dto.groq.GroqRequest;
import com.n.in.dto.groq.GroqResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;

@Service
public class GroqClient extends BaseHttpClient {

    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    public GroqResponse sendPrompt(GroqRequest groqRequest) {

        RequestStrategy strategy = (body) -> {
            try {
                return HttpRequest.newBuilder()
                        .uri(URI.create(API_URL))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .POST(HttpRequest.BodyPublishers.ofString(
                                mapper.writeValueAsString(body)
                        ))
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        HttpRequest request = strategy.build(groqRequest);

        return sendRequest(request, GroqResponse.class);
    }
}
