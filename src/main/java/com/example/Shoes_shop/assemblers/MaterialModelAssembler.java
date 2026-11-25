package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controllerV2.MaterialControllerV2;
import com.example.Shoes_shop.dto.MaterialDTO;


@Component
public class MaterialModelAssembler implements RepresentationModelAssembler<MaterialDTO,EntityModel<MaterialDTO>>{

    @Override
    public EntityModel<MaterialDTO> toModel(MaterialDTO material) {
        return EntityModel.of(material,
                linkTo(methodOn(MaterialControllerV2.class).porId(material.getId())).withSelfRel(),
                linkTo(methodOn(MaterialControllerV2.class).todos()).withRel("materiales")
        );
    }
}
