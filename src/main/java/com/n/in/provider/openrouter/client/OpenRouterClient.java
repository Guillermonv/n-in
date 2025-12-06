package com.n.in.provider.openrouter.client;

import com.n.in.provider.client.RestClientTemplate;
import com.n.in.provider.groq.config.GroqProperties;
import com.n.in.provider.groq.request.GroqRequest;
import com.n.in.provider.openrouter.reponse.OpenRouterResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;

@Service
public class OpenRouterClient extends RestClientTemplate {

    private final GroqProperties props;

    public OpenRouterClient(GroqProperties props) {
        this.props = props;
    }

    @Override
    protected HttpRequest buildRequest(Object input, String secret) throws Exception {
        GroqRequest body = (GroqRequest) input;

        String url = props.getBaseUrl() + props.getCompletionsPath();

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + secret)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();
    }

    @Override
    protected Class<OpenRouterResponse> responseType() {
        return OpenRouterResponse.class;
    }

    public OpenRouterResponse sendPrompt(GroqRequest request, String secret) {
        return execute(request, secret);
    }
}
