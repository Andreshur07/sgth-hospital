package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_contrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Boolean activo;
}