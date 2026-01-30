package br.gov.mt.artistas_api.controller;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import br.gov.mt.artistas_api.dto.AlbumRequestDTO;
import br.gov.mt.artistas_api.dto.AlbumResponseDTO;
import br.gov.mt.artistas_api.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Album> criar(@Valid @RequestBody AlbumRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public Album atualizar(
            @PathVariable Long id,
            @RequestBody AlbumRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @GetMapping
    public ResponseEntity<Page<AlbumResponseDTO>> listar(
            @RequestParam(required = false) TipoArtista tipoArtista,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        return ResponseEntity.ok(service.listar(tipoArtista, pageable));
    }
}
