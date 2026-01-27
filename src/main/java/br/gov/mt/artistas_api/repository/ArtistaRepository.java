package br.gov.mt.artistas_api.repository;

import br.gov.mt.artistas_api.domain.entity.Artista;
import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Page<Artista> findByTipo(TipoArtista tipo, Pageable pageable);

    Page<Artista> findByNomeContainingIgnoreCaseAndTipo(
            String nome,
            TipoArtista tipo,
            Pageable pageable
    );

    Page<Artista> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}

