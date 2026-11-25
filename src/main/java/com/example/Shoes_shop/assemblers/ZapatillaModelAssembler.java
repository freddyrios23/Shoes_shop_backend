package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.ZapatillaController;
import com.example.Shoes_shop.dto.ZapatillaDTO;

@Component
public class ZapatillaModelAssembler implements RepresentationModelAssembler<ZapatillaDTO, EntityModel<ZapatillaDTO>> {

    @Override
    public EntityModel<ZapatillaDTO> toModel(ZapatillaDTO zapatilla) {
        return EntityModel.of(zapatilla,
                linkTo(methodOn(ZapatillaController.class).porId(zapatilla.getId())).withSelfRel(),
                linkTo(methodOn(ZapatillaController.class).todas()).withRel("zapatillas")
        );
    }
}
