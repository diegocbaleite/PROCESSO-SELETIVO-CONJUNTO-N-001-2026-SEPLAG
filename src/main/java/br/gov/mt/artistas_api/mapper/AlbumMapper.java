package br.gov.mt.artistas_api.mapper;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.dto.AlbumResponseDTO;
import br.gov.mt.artistas_api.dto.ArtistaResponseDTO;

import java.util.stream.Collectors;

public class AlbumMapper {

    public static AlbumResponseDTO toDTO(Album album) {
        return new AlbumResponseDTO(
                album.getId(),
                album.getNome(),
                album.getArtistas().stream()
                        .map(a -> new ArtistaResponseDTO(
                                a.getId(),
                                a.getNome(),
                                a.getTipo()
                        ))
                        .collect(Collectors.toSet())
        );
    }
}
