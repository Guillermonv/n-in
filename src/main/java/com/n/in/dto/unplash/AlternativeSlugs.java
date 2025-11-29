package com.n.in.dto.unplash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlternativeSlugs {
    private String en;
    private String es;
}
