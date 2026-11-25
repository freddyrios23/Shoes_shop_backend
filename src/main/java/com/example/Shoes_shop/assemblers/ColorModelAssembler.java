package com.example.Shoes_shop.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Shoes_shop.controller.ColorController;
import com.example.Shoes_shop.dto.ColorDTO;


@Component
public class ColorModelAssembler implements RepresentationModelAssembler<ColorDTO,EntityModel<ColorDTO>>{

    @Override
    public EntityModel<ColorDTO> toModel(ColorDTO color) {
        return EntityModel.of(color,
                linkTo(methodOn(ColorController.class).porId(color.getId())).withSelfRel(),
                linkTo(methodOn(ColorController.class).todas()).withRel("colores")
        );
    }
}
