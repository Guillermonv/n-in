package com.n.in.provider.gemini.client;

import com.n.in.provider.gemini.config.GeminiProperties;
import com.n.in.provider.gemini.request.GeminiRequest;
import com.n.in.provider.gemini.response.GeminiResponse;
import com.n.in.provider.client.RestClientTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
@Service
public class GeminiClient extends RestClientTemplate {

    private final GeminiProperties props;

    public GeminiClient(GeminiProperties props) {
        this.props = props;
    }

    @Override
    protected HttpRequest buildRequest(Object input) throws Exception {
        GeminiRequest body = (GeminiRequest) input;

        String url = props.getBaseUrl() + props.getCompletionsPath();

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", props.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();
    }

    @Override
    protected Class<GeminiResponse> responseType() {
        return GeminiResponse.class;
    }

    public GeminiResponse sendPrompt(GeminiRequest request) {
        return execute(request);
    }
}
