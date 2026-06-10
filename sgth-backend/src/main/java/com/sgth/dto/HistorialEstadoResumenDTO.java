package com.sgth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialEstadoResumenDTO {

    private Integer id;
    private String funcionario;
    private String estadoAnterior;
    private String estadoNuevo;
    private String fecha;
}