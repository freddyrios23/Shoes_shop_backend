package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.MetodoEnvioController;
import com.example.Shoes_shop.dto.MetodoEnvioDTO;

@Component
public class MetodoEnvioModelAssembler implements RepresentationModelAssembler<MetodoEnvioDTO, EntityModel<MetodoEnvioDTO>> {

    @Override
    public EntityModel<MetodoEnvioDTO> toModel(MetodoEnvioDTO metodoEnvio) {
        return EntityModel.of(metodoEnvio,
                linkTo(methodOn(MetodoEnvioController.class).buscarEnvioPorId(metodoEnvio.getId())).withSelfRel(),
                linkTo(methodOn(MetodoEnvioController.class).todosLosEnvios()).withRel("metodos_envio")
        );
    }
}
