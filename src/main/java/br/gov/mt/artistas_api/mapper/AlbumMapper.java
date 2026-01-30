package br.gov.mt.artistas_api.mapper;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.dto.AlbumResponseDTO;

public class AlbumMapper {

    public static AlbumResponseDTO toDTO(Album album) {
        return new AlbumResponseDTO(
                album.getId(),
                album.getNome()
        );
    }
}
