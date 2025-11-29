package com.n.in.dto.unplash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashSearchResponse {
    private int total;
    private int total_pages;
    private List<UnsplashPhoto> results;
}