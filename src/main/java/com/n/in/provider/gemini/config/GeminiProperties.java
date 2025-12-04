package com.n.in.provider.gemini.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "clients.gemini")
public class GeminiProperties {

    private String baseUrl;
    private String completionsPath;
    private String token;

}
