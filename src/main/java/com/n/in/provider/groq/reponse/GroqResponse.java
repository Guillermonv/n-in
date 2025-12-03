package com.n.in.provider.groq.reponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroqResponse{

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<ChoiceDto> choices;
}