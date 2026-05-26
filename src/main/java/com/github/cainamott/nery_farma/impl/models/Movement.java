package com.github.cainamott.nery_farma.impl.models;

import com.github.cainamott.nery_farma.impl.enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "movimentacoes", schema = "nf_owner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // N:1 com Lotes
    @ManyToOne(optional = false)
    @JoinColumn(name = "lote_id", nullable = false)
    private Batch batch;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private MovementType type;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "motivo")
    private String reason;

    // N:1 com Usuários
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Column(name = "criado_em", columnDefinition = "TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}