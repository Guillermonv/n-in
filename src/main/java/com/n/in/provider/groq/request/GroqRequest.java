package com.n.in.provider.groq.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.n.in.provider.groq.reponse.MessageDto;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroqRequest {
    private String model;
    private List<MessageDto> messages;
}
