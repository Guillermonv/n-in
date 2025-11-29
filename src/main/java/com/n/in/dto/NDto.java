package com.n.in.dto;

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
    private String name;
    private String status;
    private String description;
    private String type;
    private String subType;
    private String categoria;
    private String subCategory;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getSubType() { return subType; }

    public void setSubType(String subType) { this.subType = subType; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getSubCategory() { return subCategory; }

    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreated() { return created; }

    public void setCreated(LocalDateTime created) { this.created = created; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
