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
 princpio_ativo             INTEGER DEFAULT 0,
 forma_farmaceutica         INTEGER DEFAULT 0,
 unidade                    INTEGER DEFAULT 0,
 estoque_minimo             INTEGER DEFAULT 1,
 ativo                      BOOLEAN NOT NULL,
 data_insercao              DATE,
 alterado_em                DATE
 CONSTRAINT pk_medicamentos PRIMARY KEY (id)
);

