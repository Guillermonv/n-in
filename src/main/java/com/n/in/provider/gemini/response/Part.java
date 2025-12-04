package com.n.in.provider.gemini.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Part {
    /**
     * El texto de la respuesta generada.
     */
    private String text;
}
