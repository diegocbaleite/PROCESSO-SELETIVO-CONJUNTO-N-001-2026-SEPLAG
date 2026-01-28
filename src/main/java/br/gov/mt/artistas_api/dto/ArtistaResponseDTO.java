package br.gov.mt.artistas_api.dto;

import br.gov.mt.artistas_api.domain.enums.TipoArtista;

public record ArtistaResponseDTO(
        Long id,
        String nome,
        TipoArtista tipo
) {
}
