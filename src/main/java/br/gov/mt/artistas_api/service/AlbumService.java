package br.gov.mt.artistas_api.service;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.domain.entity.Artista;
import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import br.gov.mt.artistas_api.dto.AlbumRequestDTO;
import br.gov.mt.artistas_api.dto.AlbumResponseDTO;
import br.gov.mt.artistas_api.repository.AlbumRepository;
import br.gov.mt.artistas_api.repository.ArtistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    // LISTAR ÁLBUNS (GET)
    public Page<AlbumResponseDTO> listar(
            TipoArtista tipoArtista,
            Pageable pageable
    ) {
        Page<Album> page;

        if (tipoArtista == null) {
            page = albumRepository.findAll(pageable);
        } else {
            page = albumRepository.findByArtistasTipo(tipoArtista, pageable);
        }

        return page.map(AlbumResponseDTO::fromEntity);
    }

    // CRIAR ÁLBUM (POST)
    public Album criar(AlbumRequestDTO dto) {

        if (dto.artistasIds() == null || dto.artistasIds().isEmpty()) {
            throw new IllegalArgumentException("É obrigatório informar ao menos um artista");
        }

        Album album = new Album();
        album.setNome(dto.nome());

        dto.artistasIds().forEach(id -> {
            Artista artista = artistaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Artista não encontrado: " + id));

            // mantém os dois lados sincronizados
            album.getArtistas().add(artista);
            artista.getAlbuns().add(album);
        });

        return albumRepository.save(album);
    }

    // ATUALIZAR ÁLBUM (PUT)
    public Album atualizar(Long id, AlbumRequestDTO dto) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        album.setNome(dto.nome());
        return albumRepository.save(album);
    }
}
