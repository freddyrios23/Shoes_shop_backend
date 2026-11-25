package com.example.Shoes_shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String rut;
    private String nombre;
    private String telefono;
    private List<Integer> boletasId;
}
