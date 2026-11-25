package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.MetodoPagoController;
import com.example.Shoes_shop.dto.MetodoPagoDTO;

@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler<MetodoPagoDTO, EntityModel<MetodoPagoDTO>> {

    @Override
    public EntityModel<MetodoPagoDTO> toModel(MetodoPagoDTO metodoPago) {
        return EntityModel.of(metodoPago,
                linkTo(methodOn(MetodoPagoController.class).buscarMetodoPagoPorId(metodoPago.getId())).withSelfRel(),
                linkTo(methodOn(MetodoPagoController.class).obtenerTodosLosPagos()).withRel("metodo_pago")
        );
    }
}
