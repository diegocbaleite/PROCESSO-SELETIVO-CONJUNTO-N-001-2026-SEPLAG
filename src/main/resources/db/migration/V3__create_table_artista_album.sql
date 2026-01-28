CREATE TABLE artista_album
(
    artista_id BIGINT NOT NULL,
    album_id   BIGINT NOT NULL,
    PRIMARY KEY (artista_id, album_id),
    CONSTRAINT fk_artista FOREIGN KEY (artista_id) REFERENCES artista (id),
    CONSTRAINT fk_album FOREIGN KEY (album_id) REFERENCES album (id)
);