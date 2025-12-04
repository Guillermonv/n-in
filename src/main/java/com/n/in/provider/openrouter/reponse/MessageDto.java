package com.n.in.provider.openrouter.reponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private String role;
    private String content;
}
