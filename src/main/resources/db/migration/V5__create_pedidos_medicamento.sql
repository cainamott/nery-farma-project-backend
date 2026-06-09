CREATE TABLE nf_owner.pedidos_medicamento (
    id                     UUID NOT NULL,
    medicamento_id         UUID NOT NULL,
    quantidade_solicitada  INTEGER NOT NULL,
    motivo                 VARCHAR(255) NOT NULL,
    status                 VARCHAR(50) NOT NULL,
    solicitante_id         UUID NOT NULL,
    movimentacao_id        UUID,
    criado_em              TIMESTAMP,
    atualizado_em          TIMESTAMP,

    CONSTRAINT pk_pedidos_medicamento PRIMARY KEY (id),

    CONSTRAINT fk_pedidos_medicamento_medicacao
        FOREIGN KEY (medicamento_id)
        REFERENCES nf_owner.medicamentos(id),

    CONSTRAINT fk_pedidos_medicamento_usuario
        FOREIGN KEY (solicitante_id)
        REFERENCES nf_owner.users(id),

    CONSTRAINT fk_pedidos_medicamento_movimentacao
        FOREIGN KEY (movimentacao_id)
        REFERENCES nf_owner.movimentacoes(id),

    -- Garante que cada movimentação seja usada apenas em um pedido
    CONSTRAINT uk_pedidos_movimentacao UNIQUE (movimentacao_id)
);

-- Indexação para melhorar performance em consultas por status ou medicamento
CREATE INDEX idx_pedidos_medicamento_status ON nf_owner.pedidos_medicamento(status);
CREATE INDEX idx_pedidos_medicamento_medicamento_id ON nf_owner.pedidos_medicamento(medicamento_id);