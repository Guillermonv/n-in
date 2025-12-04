package com.n.in.provider.unplash.client;

import com.n.in.provider.unplash.response.UnsplashSearchResponse;
import com.n.in.provider.unplash.config.UnsplashProperties;
import com.n.in.provider.client.RestClientTemplate;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpRequest;
@Service
public class UnsplashClient extends RestClientTemplate {

    private final UnsplashProperties props;

    public UnsplashClient(UnsplashProperties props) {
        this.props = props;
    }

    @Override
    protected HttpRequest buildRequest(Object input) {
        String query = input.toString();

        String url = String.format(
                "%s%s?page=1&query=%s&client_id=%s&lang=%s",
                props.getBaseUrl(),
                props.getSearchPath(),
                query,
                props.getClientId(),
                props.getLang()
        );

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();
    }

    @Override
    protected Class<UnsplashSearchResponse> responseType() {
        return UnsplashSearchResponse.class;
    }

    public UnsplashSearchResponse searchPhotos(String query) {
        return execute(query);
    }
}
