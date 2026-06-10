package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "procesos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Boolean activo;
}