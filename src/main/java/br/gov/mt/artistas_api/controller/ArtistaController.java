package br.gov.mt.artistas_api.controller;

import br.gov.mt.artistas_api.domain.enums.TipoArtista;
import br.gov.mt.artistas_api.dto.ArtistaRequestDTO;
import br.gov.mt.artistas_api.dto.ArtistaResponseDTO;
import br.gov.mt.artistas_api.service.ArtistaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private final ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ArtistaResponseDTO> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) TipoArtista tipo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome,asc") String[] sort
    ) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

        return service.listar(nome, tipo, (Pageable) pageable);
    }

    @PostMapping
    public ArtistaResponseDTO criar(@RequestBody ArtistaRequestDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public ArtistaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody ArtistaRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }
}
