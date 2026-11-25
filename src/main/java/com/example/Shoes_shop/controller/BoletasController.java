package com.example.Shoes_shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.assemblers.BoletasModelAssembler;
import com.example.Shoes_shop.model.Boletas;
import com.example.Shoes_shop.service.BoletasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "BoletasController",description = "Endpoints para gestionar todas las boletas de la zapatilla")
@RestController("BoletasController")
@RequestMapping("/api/v1/boletas")
public class BoletasController {

@Autowired
    private BoletasService boletasService;

    @Autowired
    private BoletasModelAssembler assembler;

    @Operation(
        summary = "Listar la relaciÃ³n de boletas asociadas a zapatillas"
    )
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Boletas>>> todas() {
        List<EntityModel<Boletas>> boletas = boletasService.obtenerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                boletas,
                linkTo(methodOn(BoletasController.class).todas()).withSelfRel()
        ));
    }

    @Operation(
        summary = "Asociar boleta a una zapatilla"
    )
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<String>> agregarRelacion(@RequestBody Boletas relacion) {
        try {
            String resultado = boletasService.guardarRelacion(relacion);

            EntityModel<String> recurso = EntityModel.of(resultado,
                    linkTo(methodOn(BoletasController.class).todas()).withRel("boletas_zapatillas")
            );

            return ResponseEntity
                    .created(linkTo(methodOn(BoletasController.class).todas()).toUri())
                    .body(recurso);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

