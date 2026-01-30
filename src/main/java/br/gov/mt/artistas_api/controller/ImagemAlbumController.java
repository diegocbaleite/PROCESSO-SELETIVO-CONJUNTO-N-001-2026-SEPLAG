package br.gov.mt.artistas_api.controller;

import br.gov.mt.artistas_api.service.ImagemAlbumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/albuns")
@Tag(name = "Imagens de Álbum")
public class ImagemAlbumController {

    private final ImagemAlbumService service;

    public ImagemAlbumController(ImagemAlbumService service) {
        this.service = service;
    }

    @PostMapping("/{albumId}/imagens")
    public void upload(
            @PathVariable Long albumId,
            @RequestParam("file") MultipartFile file
    ) {
        service.upload(albumId, file);
    }

    @GetMapping("/imagens/{imagemId}/link")
    public String link(@PathVariable Long imagemId) {
        return service.gerarLink(imagemId);
    }
}
