package br.gov.mt.artistas_api.controller;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.dto.AlbumRequestDTO;
import br.gov.mt.artistas_api.service.AlbumService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public Album criar(@RequestBody AlbumRequestDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public Album atualizar(
            @PathVariable Long id,
            @RequestBody AlbumRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }
}
