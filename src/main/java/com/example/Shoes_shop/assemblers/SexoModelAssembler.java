package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.SexoController;
import com.example.Shoes_shop.dto.SexoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SexoModelAssembler implements RepresentationModelAssembler<SexoDTO,EntityModel<SexoDTO>>{

    @Override
    public EntityModel<SexoDTO> toModel(SexoDTO sexo) {
        return EntityModel.of(sexo,
                linkTo(methodOn(SexoController.class).porId(sexo.getId())).withSelfRel(),
                linkTo(methodOn(SexoController.class).todos()).withRel("sexos")
        );
    }
}
