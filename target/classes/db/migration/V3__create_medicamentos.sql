CREATE TABLE nf_owner.categorias (
    id             UUID,
    nome_categoria varchar(100),
    descricao      varchar(200),
    data_criacao   date
);

CREATE TABLE nf_owner.medicamentos (
 id                         UUID NOT NULL ,
 nome_medicamento           varchar(200) NOT NULL,
 categoria_id               UUID NOT NULL,
 princpio_ativo             VARCHAR(255) NOT NULL,
 forma_farmaceutica         VARCHAR(255) NOT NULL,
 unidade                    VARCHAR(255) NOT NULL,
 estoque_minimo             INTEGER DEFAULT 1,
 ativo                      BOOLEAN NOT NULL,
 data_insercao              DATE,
 alterado_em                DATE,
 CONSTRAINT pk_medicamentos PRIMARY KEY (id)
);

