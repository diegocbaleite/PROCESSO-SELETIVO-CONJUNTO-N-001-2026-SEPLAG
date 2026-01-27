package br.gov.mt.artistas_api.service;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.domain.entity.Artista;
import br.gov.mt.artistas_api.dto.AlbumRequestDTO;
import br.gov.mt.artistas_api.repository.AlbumRepository;
import br.gov.mt.artistas_api.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    public Album criar(AlbumRequestDTO dto) {
        Album album = new Album();
        album.setNome(dto.nome());

        var artistas = new HashSet<Artista>();
        dto.artistasIds().forEach(id ->
                artistas.add(
                        artistaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Artista não encontrado"))
                )
        );

        album.setArtistas(artistas);
        return albumRepository.save(album);
    }

    public Album atualizar(Long id, AlbumRequestDTO dto) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        album.setNome(dto.nome());
        return albumRepository.save(album);
    }
}

