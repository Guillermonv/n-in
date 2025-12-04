package com.n.in.provider.openrouter.reponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenRouterResponse {

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<ChoiceDto> choices;
}