package com.example.Shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.dto.MetodoPagoDTO;
import com.example.Shoes_shop.model.MetodoPago;
import com.example.Shoes_shop.service.MetodoPagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Métodos de Pago", description = "Endpoints para gestionar los métodos de pago")
@RestController
@RequestMapping("/api/v1/metodo_pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Operation(summary = "Listar todos los métodos de pago")
    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> obtenerTodosLosPagos(){
        List<MetodoPagoDTO> metodosDePago = metodoPagoService.obtenerTodos();
        if (metodosDePago.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(metodosDePago, HttpStatus.OK);
    }

    @Operation(summary = "Buscar método de pago por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMetodoPagoPorId(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(metodoPagoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Metodo pago no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar nuevo método de pago")
    @PostMapping
    public ResponseEntity<?> agregarMetodoPago(@Valid @RequestBody MetodoPago metodoPago){
        try {
            return new ResponseEntity<>(metodoPagoService.guardarMetodoPago(metodoPago), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear metodo de pago", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Actualizar método de pago")
    @PutMapping("/{id}")
    public ResponseEntity<?> actulizarMetodoPago(@PathVariable Integer id, @Valid @RequestBody MetodoPago metodoPago){
        try {
            return new ResponseEntity<>(metodoPagoService.actualizarMetodoPago(id, metodoPago), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Eliminar método de pago")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMetodoPago(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(metodoPagoService.eliminarMetodoPAgo(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar Metodo de pago", HttpStatus.BAD_REQUEST);
        }
    }
}

