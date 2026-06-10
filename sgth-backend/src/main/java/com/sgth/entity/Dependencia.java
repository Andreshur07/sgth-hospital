package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dependencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private Boolean activo;
}