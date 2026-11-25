package com.example.Shoes_shop.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.assemblers.BoletaModelAssembler;
import com.example.Shoes_shop.dto.BoletaDTO;
import com.example.Shoes_shop.model.Boleta;
import com.example.Shoes_shop.service.BoletaService;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Boletas",description = "Endpoints para gestionar las boletas de las zapatillas")
@RestController
@RequestMapping("/api/v1/boleta")
public class BoletaController {

@Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BoletaDTO>>> todas() {
    List<EntityModel<BoletaDTO>> boletas = boletaService.obtenerTodas().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                boletas,
                linkTo(methodOn(BoletaController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BoletaDTO>> porId(@PathVariable Integer id) {
        try {
            BoletaDTO dto = boletaService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/buscar-por-zapatilla/{zapatillaId}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BoletaDTO>> porZapatilla(@PathVariable Integer zapatillaId) {
        try {
            BoletaDTO dto = boletaService.buscarPorZapatilla(zapatillaId);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BoletaDTO>> registrar(@Valid @RequestBody Boleta boleta) {
        try {
            BoletaDTO newBoletaDTO = boletaService.guardarBoleta(boleta); 
            
            return ResponseEntity
                    .created(linkTo(methodOn(BoletaController.class).porId(newBoletaDTO.getId())).toUri())
                    .body(assembler.toModel(newBoletaDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
