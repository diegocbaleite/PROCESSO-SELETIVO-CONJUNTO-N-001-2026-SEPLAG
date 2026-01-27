package br.gov.mt.artistas_api.dto;

import br.gov.mt.artistas_api.domain.enums.TipoArtista;

import java.util.Set;

public record ArtistaResponseDTO(
        Long id,
        String nome,
        TipoArtista tipo,
        Set<String> albuns
) {}
