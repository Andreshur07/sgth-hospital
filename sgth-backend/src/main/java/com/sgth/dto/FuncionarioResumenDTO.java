package com.sgth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResumenDTO {

    private Integer id;
    private String numeroDocumento;
    private String nombreCompleto;
    private String cargo;
    private String dependencia;
    private String estado;
}