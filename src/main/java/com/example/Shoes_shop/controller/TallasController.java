package com.example.Shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.model.Tallas;
import com.example.Shoes_shop.service.TallasService;





import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tallas por Zapatilla", description = "Endpoints para asociar tallas a zapatillas")
@RestController
@RequestMapping("/api/v1/tallass_zapatillas")
public class TallasController {
    
    @Autowired 
    private TallasService tallasService;

    @Operation(summary = "Listar todas las asociaciones de tallas con zapatillas")
    @GetMapping
    public ResponseEntity<List<Tallas>> todasLasTallasZapatillas(){
        List<Tallas> tallas = tallasService.obtenerTodas();
        if (tallas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tallas,HttpStatus.OK);
    }

    @Operation(summary = "Asociar una talla a una zapatilla")
    @PostMapping
    public ResponseEntity<?> agregaraRelacion(@RequestBody Tallas relacion){
        try {
            return new ResponseEntity<>(tallasService.guardarRelacion(relacion),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la relacion",HttpStatus.BAD_REQUEST);
        }
    }
}
