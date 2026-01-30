package br.gov.mt.artistas_api.dto;

import br.gov.mt.artistas_api.domain.entity.Album;

public record AlbumResponseDTO(
        Long id,
        String nome
) {
    public static AlbumResponseDTO fromEntity(Album album) {
        return new AlbumResponseDTO(
                album.getId(),
                album.getNome()
        );
    }
}
