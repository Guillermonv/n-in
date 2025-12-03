package com.n.in.provider.groq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "clients.groq")
public class GroqProperties {

    private String baseUrl;
    private String completionsPath;
    private String token;

}
