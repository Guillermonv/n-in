package com.n.in.provider.openrouter.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.n.in.provider.groq.reponse.Message;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenRouterRequest {
    private String model;
    private List<Message> messages;
}
