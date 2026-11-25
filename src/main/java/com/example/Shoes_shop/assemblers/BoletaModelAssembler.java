package com.example.Shoes_shop.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controllerV2.BoletaControllerV2;
import com.example.Shoes_shop.dto.BoletaDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<BoletaDTO, EntityModel<BoletaDTO>>{

    @Override
        public EntityModel<BoletaDTO> toModel(BoletaDTO boleta) {
            return EntityModel.of(boleta,
                linkTo(methodOn(BoletaControllerV2.class).porId(boleta.getId())).withSelfRel(),
                linkTo(methodOn(BoletaControllerV2.class).todas()).withRel("boletas")
            );
        }
}
