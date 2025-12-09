package com.n.in.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NDto {

    private Long id;
    private String title;
    private String shortDescription;
    private String message;
    private String status;
    private String type;
    private String subType;
    private String category;
    private String subCategory;

    private String imagePrompt;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;

}
