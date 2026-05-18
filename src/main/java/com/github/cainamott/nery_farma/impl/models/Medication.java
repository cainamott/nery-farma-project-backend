package com.github.cainamott.nery_farma.impl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "nome_medicamento")
    private String medicationName;

    @Column(name = "categoria")
    @ManyToOne(optional = false)
    @JoinColumn
    private Category categoryId;

    @Column(name = "princpio_ativo")
    private String activePrinciple; // Adicionar Enum para os diferentes principios ativos

    @Column(name = "forma_farmaceutica")
    private String dosageForm;      // Add enum para formas farmaceuticas (liquido, solido, etc)

    @Column(name = "unidade")
    private String unitary;         //Add enum para maior controle do tipo de unidade do medicamento

    @Column(name = "estoque_minimo")
    private Integer minimalStorage;

    @Column(name = "data_insercao")
    private Date insertionDate;

    @Column(name = "ativo")
    private Boolean isActive;
}
