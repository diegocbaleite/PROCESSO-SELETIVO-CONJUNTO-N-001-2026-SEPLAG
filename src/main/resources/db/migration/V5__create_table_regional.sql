CREATE TABLE IF NOT EXISTS regional
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    codigo_externo
    INTEGER
    NOT
    NULL,
    nome
    VARCHAR
(
    200
) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
