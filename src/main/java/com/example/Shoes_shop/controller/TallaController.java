package com.example.Shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.dto.TallaDTO;
import com.example.Shoes_shop.model.Talla;
import com.example.Shoes_shop.service.TallaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Tallas", description = "Endpoints para gestionar las tallas de calzado")
@RestController
@RequestMapping("/api/v1/talla")
public class TallaController {
    @Autowired
    private TallaService tallaService;

    @Operation(summary = "Listar todas las tallas")
    @GetMapping
    public ResponseEntity<List<TallaDTO>> todasLasTallas(){
        List<TallaDTO> talla = tallaService.obtenerTodas();
        if (talla.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(talla,HttpStatus.OK);
    } 

    @Operation(summary = "Buscar talla por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTallaPorId(@PathVariable Integer id){
        try {
            TallaDTO talla = tallaService.buscarPorId(id);
            return new ResponseEntity<>(talla,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no se encontro la marca",HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar nueva talla")
    @PostMapping
    public ResponseEntity<?> agregartalla (@Valid @RequestBody Talla talla){
        try {
            return new ResponseEntity<>(tallaService.guardarTalla(talla),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la talla",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar talla")
    @PatchMapping("/{id}")
    public ResponseEntity<?> editarMarca(@PathVariable Integer id,@Valid @RequestBody Talla talla){
        try {
            Talla editada = tallaService.actualizarTalla(id, talla);
            return new ResponseEntity<>(editada,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("talla no encontrada",HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar talla por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTalla (@PathVariable Integer id){
        String resultado = tallaService.eliminarTalla(id);

        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado,HttpStatus.NOT_FOUND);
        }
    }
}
