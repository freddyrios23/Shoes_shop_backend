package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.MarcaController;
import com.example.Shoes_shop.dto.MarcaDTO;


@Component
public class MarcaModelAssembler implements RepresentationModelAssembler<MarcaDTO,EntityModel<MarcaDTO>>{

    @Override
    public EntityModel<MarcaDTO> toModel(MarcaDTO marca) {
        return EntityModel.of(marca,
                linkTo(methodOn(MarcaController.class).porId(marca.getId())).withSelfRel(),
                linkTo(methodOn(MarcaController.class).todas()).withRel("marcas")
        );
    }
}
