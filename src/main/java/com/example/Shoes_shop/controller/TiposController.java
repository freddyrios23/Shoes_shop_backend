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

import com.example.Shoes_shop.assemblers.TiposModelAssembler;
import com.example.Shoes_shop.model.Tipos;
import com.example.Shoes_shop.service.TiposService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tipos de zapatillas", description = "Endpoints para asociar tipos con zapatillas")
@RestController
@RequestMapping("/api/v1/tipos_zapatillas")
public class TiposController {
    @Autowired
    private TiposService tiposService;

    @Autowired
    private TiposModelAssembler assembler;

    @Operation(
        summary = "Listar Relaciones de tipos"
    )
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Tipos>>> todos() {
        List<EntityModel<Tipos>> tipos = tiposService.obtenerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (tipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                tipos,
                linkTo(methodOn(TiposController.class).todos()).withSelfRel()
        ));
    }

    @Operation(
        summary = "Asociar tipo a zapatilla"
    )
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<String>> agregarRelacion(@RequestBody Tipos relacion) {
        try {
            String resultado = tiposService.guardarRelacion(relacion);

            EntityModel<String> recurso = EntityModel.of(resultado,
                    linkTo(methodOn(TiposController.class).todos()).withRel("tipos_zapatillas")
            );

            return ResponseEntity
                    .created(linkTo(methodOn(TiposController.class).todos()).toUri())
                    .body(recurso);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

