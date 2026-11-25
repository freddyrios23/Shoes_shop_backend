package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.TallaController;
import com.example.Shoes_shop.dto.TallaDTO;

@Component
public class TallaModelAssembler implements RepresentationModelAssembler<TallaDTO, EntityModel<TallaDTO>> {

    @Override
    public EntityModel<TallaDTO> toModel(TallaDTO talla) {
        return EntityModel.of(talla,
                linkTo(methodOn(TallaController.class).buscarTallaPorId(talla.getId())).withSelfRel(),
                linkTo(methodOn(TallaController.class).todasLasTallas()).withRel("tallas")
        );
    }
}
