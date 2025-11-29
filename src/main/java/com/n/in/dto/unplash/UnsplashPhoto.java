package com.n.in.dto.unplash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashPhoto {
    private String id;
    private String slug;
    private AlternativeSlugs alternative_slugs;
    private String created_at;
    private String updated_at;
    private String promoted_at;
    private int width;
    private int height;
    private String color;
    private String blur_hash;
    private String description;
    private String alt_description;
    private List<Object> breadcrumbs;
    private UnsplashUrls urls;
    private UnsplashLinks links;
    private int likes;
    private boolean liked_by_user;
    private boolean bookmarked;
    private List<Object> current_user_collections;
    private Object sponsorship;
    private Map<String, Object> topic_submissions;
    private String asset_type;
}
