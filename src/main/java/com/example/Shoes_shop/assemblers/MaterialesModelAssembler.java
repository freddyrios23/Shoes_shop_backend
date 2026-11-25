package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controllerV2.MaterialesControllerV2;
import com.example.Shoes_shop.model.Materiales;


@Component
public class MaterialesModelAssembler implements RepresentationModelAssembler<Materiales,EntityModel<Materiales>>{

    @Override
    public EntityModel<Materiales> toModel(Materiales materiales) {
        return EntityModel.of(materiales,
                linkTo(methodOn(MaterialesControllerV2.class).todos()).withRel("material_zapatillas")
        );
    }
}
