package com.n.in.provider.unplash.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "clients.unsplash")
public class UnsplashProperties {

    private String baseUrl;
    private String searchPath;
    private String clientId;
    private String lang;

}
