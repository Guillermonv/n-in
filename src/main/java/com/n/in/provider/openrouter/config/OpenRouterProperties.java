package com.n.in.provider.openrouter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "clients.openrouter")
public class OpenRouterProperties {

    private String baseUrl;
    private String completionsPath;
    private String token;

}
