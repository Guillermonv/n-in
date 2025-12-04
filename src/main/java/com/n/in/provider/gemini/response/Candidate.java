package com.n.in.provider.gemini.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {
    private Integer index;


    private Content content;


    @JsonProperty("finishReason")
    private String finishReason;

    // Aquí iría el campo de filtros de seguridad si fuera necesario:
    // @JsonProperty("safetyRatings")
    // private List<SafetyRatingDto> safetyRatings;
}