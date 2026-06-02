CREATE TABLE nf_owner.lotes (
    id UUID NOT NULL PRIMARY KEY,
    medicamento_id UUID NOT NULL,
    numero_do_lote INTEGER NOT NULL,
    data_de_fabricacao DATE,
    data_de_validade DATE NOT NULL,
    quantidade_atual INTEGER NOT NULL,

    CONSTRAINT fk_lotes_medicamento
        FOREIGN KEY (medicamento_id)
        REFERENCES nf_owner.medicamentos(id)
);

CREATE INDEX idx_lotes_data_de_validade
    ON nf_owner.lotes(data_de_validade);


CREATE TABLE nf_owner.movimentacoes (
    id UUID NOT NULL PRIMARY KEY,
    lote_id UUID NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    quantidade INTEGER NOT NULL,
    motivo VARCHAR(255),
    usuario_id UUID NOT NULL,
    criado_em TIMESTAMP,

    CONSTRAINT fk_movimentacoes_lote
        FOREIGN KEY (lote_id)
        REFERENCES nf_owner.lotes(id),

    CONSTRAINT fk_movimentacoes_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES nf_owner.users(id)
);