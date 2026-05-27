package com.github.cainamott.nery_farma.impl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "lotes", schema = "nf_owner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medication medication;

    @Column(name = "numero_do_lote", nullable = false)
    private Integer batchNumber;

    @Column(name = "data_de_fabricacao", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Date fabricationDate;

    @Column(name = "data_de_validade", columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "quantidade_atual", nullable = false)
    private Integer currentQuantity;
}