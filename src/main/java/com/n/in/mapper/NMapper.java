package com.n.in.mapper;


import com.n.in.dto.NDto;
import com.n.in.dto.unplash.UnsplashSearchResponse;
import com.n.in.repository.NEntity;
import org.springframework.stereotype.Component;

@Component
public class NMapper {

    public NDto toDto(NEntity e) {
        if (e == null) return null;
        return NDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .name(e.getName())
                .status(e.getStatus())
                .description(e.getDescription())
                .type(e.getType())
                .subType(e.getSubType())
                .categoria(e.getCategoria())
                .subCategory(e.getSubCategory())
                .imageUrl(e.getImageUrl())
                .created(e.getCreated())
                .lastUpdated(e.getLastUpdated())
                .build();
    }

    public NEntity toEntity(NDto d) {
        if (d == null) return null;
        return NEntity.builder()
                .id(d.getId())
                .title(d.getTitle())
                .name(d.getName())
                .status(d.getStatus())
                .description(d.getDescription())
                .type(d.getType())
                .subType(d.getSubType())
                .categoria(d.getCategoria())
                .subCategory(d.getSubCategory())
                .imageUrl(d.getImageUrl())
                .created(d.getCreated())
                .lastUpdated(d.getLastUpdated())
                .build();
    }
}
