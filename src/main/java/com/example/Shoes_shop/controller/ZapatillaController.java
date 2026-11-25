package com.example.Shoes_shop.controllerV2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.assemblers.ZapatillaModelAssembler;
import com.example.Shoes_shop.dto.ZapatillaDTO;
import com.example.Shoes_shop.model.Zapatilla;
import com.example.Shoes_shop.service.ZapatillaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Zapatillas", description = "Endpoints para gestion zapatillas")
@RestController
@RequestMapping("/api/v1/zapatillas")
public class ZapatillaController {
    
    @Autowired
    private ZapatillaService zapatillaService;

    @Autowired
    private ZapatillaModelAssembler assembler;

    @Operation(
        summary = "Listar Zapatillas",
        description = "Obtiene todas las zapatillas que stan registradas en el sistema"
    )
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ZapatillaDTO>>> todas() {
        List<EntityModel<ZapatillaDTO>> zapatillas = zapatillaService.obtenerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (zapatillas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                zapatillas,
                linkTo(methodOn(ZapatillaControllerV2.class).todas()).withSelfRel()
        ));
    }

    @Operation(
        summary = "Buscar Zapatilla",
        description = "Obtiene La zapatilla por un id especifico"
    )
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ZapatillaDTO>> porId(@PathVariable Integer id) {
        try {
            ZapatillaDTO dto = zapatillaService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Agregar Zapatilla",
        description = "Registra una nueva zapatilla en el sistema"
    )
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ZapatillaDTO>> registrar(@Valid @RequestBody Zapatilla zapatilla) {
        try {
            ZapatillaDTO newZapatilla = zapatillaService.guardarZapatilla(zapatilla);
            return ResponseEntity
                    .created(linkTo(methodOn(ZapatillaControllerV2.class).porId(newZapatilla.getId())).toUri())
                    .body(assembler.toModel(newZapatilla));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
        summary = "Editar Zapatilla",
        description = "Edita una zapatilla por un id especifico"
    )      
    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ZapatillaDTO>> updateZapatilla(@PathVariable Integer id, @RequestBody Zapatilla zapatilla) {
        try {
            Zapatilla zapatillaActualizada = zapatillaService.actualizarZapatilla(id, zapatilla);

            ZapatillaDTO zapatillaDTO = zapatillaService.buscarPorId(zapatillaActualizada.getId());

            return ResponseEntity.ok(assembler.toModel(zapatillaDTO));

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(
        summary = "Elmina Zapatilla",
        description = "elimina una zapatilla por un id especifico"
    )
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteZapatilla(@PathVariable Integer id){
        zapatillaService.eliminarZapatilla(id);
        return ResponseEntity.noContent().build();
    }
}

