package com.n.in.dto.unplash;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashUrls {
    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;
    private String small_s3;
}
