package br.gov.mt.artistas_api.repository;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("""
        SELECT DISTINCT a
        FROM Album a
        JOIN a.artistas ar
        WHERE ar.tipo = :tipo
    """)
    Page<Album> findByArtistasTipo(
            @Param("tipo") TipoArtista tipo,
            Pageable pageable
    );
}


