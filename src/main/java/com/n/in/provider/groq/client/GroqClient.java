package com.n.in.provider.groq.client;

import com.n.in.provider.groq.config.GroqProperties;
import com.n.in.provider.groq.request.GroqRequest;
import com.n.in.provider.groq.reponse.GroqResponse;
import com.n.in.provider.client.SimpleRestClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
@Service
public class GroqClient extends SimpleRestClient {

    private final GroqProperties props;

    public GroqClient(GroqProperties props) {
        this.props = props;
    }

    @Override
    protected HttpRequest buildRequest(Object input) throws Exception {
        GroqRequest body = (GroqRequest) input;

        String url = props.getBaseUrl() + props.getCompletionsPath();

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + props.getToken())
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                .build();
    }

    @Override
    protected Class<GroqResponse> responseType() {
        return GroqResponse.class;
    }

    public GroqResponse sendPrompt(GroqRequest request) {
        return execute(request);
    }
}
