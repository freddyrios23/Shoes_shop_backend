package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controllerV2.ColoresControllerV2;
import com.example.Shoes_shop.model.Colores;


@Component
public class ColoresModelAssembler implements RepresentationModelAssembler<Colores,EntityModel<Colores>>{

    @Override
    public EntityModel<Colores> toModel(Colores colores) {
        return EntityModel.of(colores,
                linkTo(methodOn(ColoresControllerV2.class).todos()).withRel("color_zapatillas")
        );
    }
}
