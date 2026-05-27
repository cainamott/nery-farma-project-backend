package com.github.cainamott.nery_farma.impl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "medicamentos", schema = "nf_owner")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_medicamento")
    private String medicationName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id") // This forces Hibernate to look for 'categoria_id' instead of 'category_id_id'
    private Category category;

    @Column(name = "princpio_ativo")
    private String activePrinciple; // Adicionar Enum para os diferentes principios ativos

    @Column(name = "forma_farmaceutica")
    private String dosageForm;      // Add enum para formas farmaceuticas (liquido, solido, etc)

    @Column(name = "unidade")
    private String unitary;         //Add enum para maior controle do tipo de unidade do medicamento

    @Column(name = "estoque_minimo")
    private Integer minimalStorage;

    @Column(name = "data_insercao", columnDefinition = "DATE")
    private Date insertionDate;

    @Column(name = "ativo")
    private Boolean isActive;
}
