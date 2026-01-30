package br.gov.mt.artistas_api.repository;

import br.gov.mt.artistas_api.domain.entity.ImagemAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemAlbumRepository extends JpaRepository<ImagemAlbum, Long> {

    List<ImagemAlbum> findByAlbumId(Long albumId);
}
