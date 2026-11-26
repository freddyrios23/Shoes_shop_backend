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

import com.example.Shoes_shop.dto.ComunaDTO;
import com.example.Shoes_shop.model.Comuna;
import com.example.Shoes_shop.service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Comunas", description = "Endpoints para gestionar comunas")
@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @Operation(summary = "Listar todas las comunas")
    @GetMapping
    public ResponseEntity<List<ComunaDTO>> todasLascomunas(){
        List<ComunaDTO> comunas = comunaService.obtenerTodas();
        if (comunas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas,HttpStatus.OK);
    }

    @Operation(summary = "Buscar comuna por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarComunaPorId(@PathVariable Integer id){
        try {
            ComunaDTO comuna = comunaService.buscarPorID(id);
            return new ResponseEntity<>(comuna,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no se encontro la comuna",HttpStatus.NOT_FOUND);
        }    
    }

    @Operation(summary = "Registrar nueva comuna")
    @PostMapping
    public ResponseEntity<?> agregarComuna(@Valid @RequestBody Comuna comuna){
        try {
            return new ResponseEntity<>(comunaService.guardarComuna(comuna),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo la comuna",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar comuna")
    @PatchMapping("/{id}")
    public ResponseEntity<?> editarComuna (@PathVariable Integer id , @Valid @RequestBody Comuna comuna){
        try {
            Comuna editada = comunaService.actualizarComuna(id, comuna);
            return new ResponseEntity<>(editada,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("comuna no encontrada",HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar comuna por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComuna(@PathVariable Integer id){
        String resultado = comunaService.eliminarComuna(id);

        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resultado,HttpStatus.NOT_FOUND);
        }
    }
    
}

