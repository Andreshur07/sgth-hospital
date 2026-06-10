package com.sgth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    private String asunto;

    private String mensaje;

    @Column(name = "correo_destino")
    private String correoDestino;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    private String estado;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;
}