package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.BoletasController;
import com.example.Shoes_shop.model.Boletas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BoletasModelAssembler implements RepresentationModelAssembler<Boletas, EntityModel<Boletas>> {
    @Override
    public EntityModel<Boletas> toModel(Boletas boletas) {
    return EntityModel.of(boletas,

            linkTo(methodOn(BoletasController.class).todas()).withRel("boletas_boleta")
        );
    }
}
