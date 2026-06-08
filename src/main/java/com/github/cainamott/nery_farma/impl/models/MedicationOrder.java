package com.github.cainamott.nery_farma.impl.models;

import com.github.cainamott.nery_farma.auth.models.User;
import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "pedidos_medicamento", schema = "nf_owner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medication medication;

    @Column(name = "quantidade_solicitada", nullable = false)
    private Integer requestedQuantity;

    @Column(name = "motivo", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MedicationOrderStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solicitante_id", nullable = false)
    private User requester;

    @OneToOne
    @JoinColumn(name = "movimentacao_id", unique = true)
    private Movement movement;

    @Column(name = "criado_em", columnDefinition = "TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "atualizado_em", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
