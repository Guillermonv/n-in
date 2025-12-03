package com.n.in.provider.unplash.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashLinks {
    private String self;
    private String html;
    private String download;
    private String download_location;
}
