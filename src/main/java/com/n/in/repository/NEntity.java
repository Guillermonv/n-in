package com.n.in.repository;


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
public class NEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String name;

    @Column(length = 20)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String type;

    @Column(name = "sub_type", length = 20)
    private String subType;

    @Column(length = 20)
    private String categoria;

    @Column(name = "sub_category", length = 20)
    private String subCategory;

    private String imageUrl;

    private LocalDateTime created;
    private LocalDateTime lastUpdated;
}
