package br.gov.mt.artistas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record AlbumRequestDTO(
        @NotBlank
        String nome,
        @NotEmpty
        Set<Long> artistasIds
) {
}
