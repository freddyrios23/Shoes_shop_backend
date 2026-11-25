package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.BoletaController;
import com.example.Shoes_shop.dto.BoletaDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<BoletaDTO, EntityModel<BoletaDTO>>{

    @Override
        public EntityModel<BoletaDTO> toModel(BoletaDTO boleta) {
            return EntityModel.of(boleta,
                linkTo(methodOn(BoletaController.class).porId(boleta.getId())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).todas()).withRel("boletas")
            );
        }
}
