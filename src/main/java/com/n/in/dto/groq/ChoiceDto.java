package com.n.in.dto.groq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoiceDto {
    private Integer index;
    private MessageDto message;


}
