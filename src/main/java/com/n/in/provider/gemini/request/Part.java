package com.n.in.provider.gemini.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Part {
    /**
     * El contenido del mensaje (el prompt del usuario).
     */
    private String text;

    // Si fuera necesario soportar imágenes, se añadirían campos aquí:
    // private InlineData inlineData;
}