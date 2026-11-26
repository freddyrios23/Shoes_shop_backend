package com.example.Shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Shoes_shop.dto.RegionDTO;
import com.example.Shoes_shop.model.Region;
import com.example.Shoes_shop.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Regiones", description = "Endpoints para gestionar regiones")
@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Operation(summary = "Listar todas las regiones")
    @GetMapping
    public ResponseEntity<List<RegionDTO>> obtenerTodas() {
        List<RegionDTO> regiones = regionService.obtenerTodas();

        if (regiones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    @Operation(summary = "Buscar región por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRegionPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(regionService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Región no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar nueva región")
    @PostMapping
    public ResponseEntity<?> agregarRegion(@Valid @RequestBody Region region) {
        try {
            return new ResponseEntity<>(regionService.guardarRegion(region), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear región", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar región")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRegion(@PathVariable Integer id, @RequestBody Region region) {
        try {
            return new ResponseEntity<>(regionService.actualizarRegion(id, region), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar región", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Eliminar región")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(regionService.eliminarRegion(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar región", HttpStatus.BAD_REQUEST);
        }
    }
}
