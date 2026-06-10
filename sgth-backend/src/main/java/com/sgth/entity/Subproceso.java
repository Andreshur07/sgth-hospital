package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subprocesos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subproceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    private String nombre;

    private Boolean activo;
}