package com.n.in.dto.groq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroqRequest {
    private String model;
    private List<MessageDto> messages;
}
