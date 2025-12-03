package com.n.in.provider.unplash.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlternativeSlugs {
    private String en;
    private String es;
}
