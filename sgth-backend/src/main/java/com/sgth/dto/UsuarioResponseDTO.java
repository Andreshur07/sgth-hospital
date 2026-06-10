package com.sgth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private Boolean activo;
    private String nombreFuncionario;
}