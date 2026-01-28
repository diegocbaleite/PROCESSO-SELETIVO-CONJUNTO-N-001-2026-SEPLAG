package br.gov.mt.artistas_api.dto;

import java.util.Set;

public record AlbumResponseDTO(
        Long id,
        String nome,
        Set<ArtistaResponseDTO> artistas
) {
}
