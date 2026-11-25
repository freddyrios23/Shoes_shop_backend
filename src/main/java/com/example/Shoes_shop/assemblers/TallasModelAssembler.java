package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.TallasController;
import com.example.Shoes_shop.model.Tallas;

@Component
public class TallasModelAssembler implements RepresentationModelAssembler<Tallas, EntityModel<Tallas>> {

    @Override
    public EntityModel<Tallas> toModel(Tallas talla) {
        return EntityModel.of(talla,
                linkTo(methodOn(TallasController.class).todasLasTallasZapatillas()).withRel("tallas_zapatillas")
        );
    }
}
