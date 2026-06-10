package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados_funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private Boolean activo;
}
