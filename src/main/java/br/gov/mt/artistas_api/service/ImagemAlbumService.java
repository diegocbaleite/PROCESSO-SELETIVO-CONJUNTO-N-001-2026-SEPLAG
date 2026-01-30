package br.gov.mt.artistas_api.service;

import br.gov.mt.artistas_api.domain.entity.Album;
import br.gov.mt.artistas_api.domain.entity.ImagemAlbum;
import br.gov.mt.artistas_api.repository.AlbumRepository;
import br.gov.mt.artistas_api.repository.ImagemAlbumRepository;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ImagemAlbumService {

    private final MinioClient minioClient;
    private final ImagemAlbumRepository imagemRepository;
    private final AlbumRepository albumRepository;

    private static final String BUCKET = "albuns";

    public ImagemAlbumService(
            MinioClient minioClient,
            ImagemAlbumRepository imagemRepository,
            AlbumRepository albumRepository
    ) {
        this.minioClient = minioClient;
        this.imagemRepository = imagemRepository;
        this.albumRepository = albumRepository;
    }

    public void upload(Long albumId, MultipartFile file) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        String nomeArquivo = UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET)
                            .object(nomeArquivo)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            ImagemAlbum imagem = ImagemAlbum.builder()
                    .nomeArquivo(nomeArquivo)
                    .contentType(file.getContentType())
                    .album(album)
                    .build();

            imagemRepository.save(imagem);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar imagem", e);
        }
    }

    public String gerarLink(Long imagemId) {

        ImagemAlbum imagem = imagemRepository.findById(imagemId)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));

        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(BUCKET)
                            .object(imagem.getNomeArquivo())
                            .method(Method.GET)
                            .expiry(30, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar link", e);
        }
    }
}
