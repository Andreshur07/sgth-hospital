package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String categoria;

    private Boolean obligatorio;

    @Column(name = "requiere_vencimiento")
    private Boolean requiereVencimiento;

    private Boolean activo;
}
