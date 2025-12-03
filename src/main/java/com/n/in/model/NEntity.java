package com.n.in.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "n")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class NEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 20)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String type;

    @Column(name = "sub_type", length = 20)
    private String subType;

    @Column(length = 20)
    private String category;

    @Column(name = "sub_category", length = 20)
    private String subCategory;

    @Column(name = "image_prompt", length = 250)
    private String imagePrompt;

    private String imageUrl;

    private LocalDateTime created;
    private LocalDateTime lastUpdated;
}
