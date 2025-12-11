package com.n.in.model.mapper;


import com.n.in.model.dto.NDto;
import com.n.in.model.NEntity;
import org.springframework.stereotype.Component;

@Component
public class NMapper {

    public NDto toDto(NEntity e) {
        if (e == null) return null;
        return NDto.builder()
                .id(e.getId())
                .shortDescription(e.getShortDescription())
                .status(e.getStatus())
                .message(e.getMessage())
                .type(e.getType())
                .subType(e.getSubType())
                .category(e.getCategory())
                .subCategory(e.getSubCategory())
                .imageUrl(e.getImageUrl())
                .imagePrompt(e.getImagePrompt())
                .created(e.getCreated())
                .lastUpdated(e.getLastUpdated())
                .build();
    }

    public NEntity toEntity(NDto d) {
        if (d == null) return null;
        return NEntity.builder()
                .id(d.getId())
                .status(d.getStatus())
                .title(d.getTitle())
                .shortDescription(d.getShortDescription())
                .message(d.getMessage())
                .type(d.getType())
                .subType(d.getSubType())
                .category(d.getCategory())
                .subCategory(d.getSubCategory())
                .imagePrompt(d.getImagePrompt())
                .imageUrl(d.getImageUrl())
                .created(d.getCreated())
                .lastUpdated(d.getLastUpdated())
                .build();
    }
}
