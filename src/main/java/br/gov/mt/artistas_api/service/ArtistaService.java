package br.gov.mt.artistas_api.service;

import br.gov.mt.artistas_api.domain.entity.Artista;
import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import br.gov.mt.artistas_api.dto.ArtistaRequestDTO;
import br.gov.mt.artistas_api.dto.ArtistaResponseDTO;
import br.gov.mt.artistas_api.repository.ArtistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaService(ArtistaRepository repository) {
        this.repository = repository;
    }

    public Page<ArtistaResponseDTO> listar(
            String nome,
            TipoArtista tipo,
            Pageable pageable
    ) {
        Page<Artista> page;

        if (nome != null && tipo != null) {
            page = repository.findByNomeContainingIgnoreCaseAndTipo(
                    nome,
                    tipo,
                    pageable
            );
        } else if (nome != null) {
            page = repository.findByNomeContainingIgnoreCase(
                    nome,
                    pageable
            );
        } else if (tipo != null) {
            page = repository.findByTipo(
                    tipo,
                    pageable
            );
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(this::toDTO);
    }

    public ArtistaResponseDTO criar(ArtistaRequestDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.nome());
        artista.setTipo(dto.tipo());

        return toDTO(repository.save(artista));
    }

    public ArtistaResponseDTO atualizar(Long id, ArtistaRequestDTO dto) {
        Artista artista = repository.findById(id).orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        artista.setNome(dto.nome());
        artista.setTipo(dto.tipo());

        return toDTO(repository.save(artista));
    }

    private ArtistaResponseDTO toDTO(Artista artista) {
        return new ArtistaResponseDTO(
                artista.getId(),
                artista.getNome(),
                artista.getTipo(),
                artista.getAlbuns() == null
                        ? Set.of()
                        : artista.getAlbuns()
                        .stream()
                        .map(a -> a.getNome())
                        .collect(Collectors.toSet())
        );
    }
}
