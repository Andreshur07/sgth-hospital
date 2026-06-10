package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private String tipo;

    private String mensaje;

    @Column(name = "fecha_alerta")
    private LocalDate fechaAlerta;

    private String estado;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;
}