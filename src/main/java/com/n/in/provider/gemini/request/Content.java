package com.n.in.provider.gemini.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    /**
     * Lista de objetos PartDto, que contiene la información real
     * del mensaje (texto, imagen, etc.).
     */
    private List<Part> parts;

    // Opcional: Para APIs que lo requieren, define el rol (user, model, system)
    // private String role;
}