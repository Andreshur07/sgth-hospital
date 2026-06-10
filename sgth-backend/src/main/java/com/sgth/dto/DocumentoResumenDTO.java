package com.sgth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoResumenDTO {

    private Integer id;
    private String nombreArchivo;
    private String tipoDocumento;
    private String fechaVencimiento;
    private String estado;
    private String nombreFuncionario;

}