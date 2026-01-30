package br.gov.mt.artistas_api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagemAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    private String contentType;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
}
