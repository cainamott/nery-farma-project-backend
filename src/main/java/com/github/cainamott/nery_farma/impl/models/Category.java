package com.github.cainamott.nery_farma.impl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "categorias") //, schema = "nf_owner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_categoria")
    private String label;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_criacao", columnDefinition = "DATE")
    private Date creationDate;

}
