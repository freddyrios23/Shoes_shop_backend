package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.TiposController;
import com.example.Shoes_shop.model.Tipos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TiposModelAssembler implements RepresentationModelAssembler<Tipos,EntityModel<Tipos>>{

    @Override
    public EntityModel<Tipos> toModel(Tipos tipo) {
        return EntityModel.of(tipo,
                linkTo(methodOn(TiposController.class).todos()).withRel("tipos_zapatillas")
        );
    }
}
