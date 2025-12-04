package com.n.in.provider.gemini.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
    /**
     * Lista de los candidatos de respuesta generados por el modelo.
     * En la mayoría de los casos solo habrá un candidato.
     */
    private List<Candidate> candidates;

    // Aquí iría el UsageMetadataDto (tokens usados, etc.)
    // private UsageMetadataDto usageMetadata;
}