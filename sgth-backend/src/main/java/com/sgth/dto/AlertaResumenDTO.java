package com.sgth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaResumenDTO {

    private Integer id;
    private String funcionario;
    private String tipo;
    private String fechaAlerta;
    private String estado;
}