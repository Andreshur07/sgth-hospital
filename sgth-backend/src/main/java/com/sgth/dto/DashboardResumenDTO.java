package com.sgth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResumenDTO {

    private Long totalFuncionarios;
    private Long funcionariosActivos;
    private Long funcionariosInactivos;

    private Long totalDocumentos;
    private Long documentosVigentes;
    private Long documentosVencidos;

    private Long alertasPendientes;

    private Long usuariosActivos;
}