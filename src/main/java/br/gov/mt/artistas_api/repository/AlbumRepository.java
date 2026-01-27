package br.gov.mt.artistas_api.repository;

import br.gov.mt.artistas_api.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
