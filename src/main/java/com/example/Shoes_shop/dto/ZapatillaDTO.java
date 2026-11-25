package com.example.Shoes_shop.dto;

import lombok.Data;

@Data
public class ZapatillaDTO {
    private Integer id;
    private String nombre;
    private Integer precio;
    private Integer marcaId;
    private String nombreMarca;
    private BoletaDTO boleta;
}
