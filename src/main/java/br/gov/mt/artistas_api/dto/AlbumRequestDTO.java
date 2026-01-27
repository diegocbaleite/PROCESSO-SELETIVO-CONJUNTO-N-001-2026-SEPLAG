package br.gov.mt.artistas_api.dto;

import java.util.Set;

public record AlbumRequestDTO(
        String nome,
        Set<Long> artistasIds
) {}
