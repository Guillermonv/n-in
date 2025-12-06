package com.n.in.model.mapper;


import com.n.in.model.NDto;
import com.n.in.model.NEntity;
import org.springframework.stereotype.Component;

@Component
public class NMapper {

    public NDto toDto(NEntity e) {
        if (e == null) return null;
        return NDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .status(e.getStatus())
                .description(e.getDescription())
                .type(e.getType())
                .subType(e.getSubType())
                .categoria(e.getCategory())
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
                .title(d.getTitle())
                .status(d.getStatus())
                .description(d.getDescription())
                .type(d.getType())
                .subType(d.getSubType())
                .category(d.getCategoria())
                .subCategory(d.getSubCategory())
                .imagePrompt(d.getImagePrompt())
                .imageUrl(d.getImageUrl())
                .created(d.getCreated())
                .lastUpdated(d.getLastUpdated())
                .build();
    }
}
