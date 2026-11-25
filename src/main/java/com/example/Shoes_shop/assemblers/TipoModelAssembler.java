package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controllerV2.TipoControllerV2;
import com.example.Shoes_shop.dto.TipoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TipoModelAssembler implements RepresentationModelAssembler<TipoDTO,EntityModel<TipoDTO>>{

    @Override
    public EntityModel<TipoDTO> toModel(TipoDTO tipo) {
        return EntityModel.of(tipo,
            linkTo(methodOn(TipoControllerV2.class).porId(tipo.getId())).withSelfRel(),
                linkTo(methodOn(TipoControllerV2.class).todos()).withRel("tipos")
        );
    }
}
