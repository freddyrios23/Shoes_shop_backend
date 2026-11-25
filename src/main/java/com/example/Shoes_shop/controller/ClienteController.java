package com.example.Shoes_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shoes_shop.dto.ClienteDTO;
import com.example.Shoes_shop.model.Cliente;
import com.example.Shoes_shop.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints para gestionar los clientes")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar todos los clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> todosLosClientes(){
        List<ClienteDTO> clientes = clienteService.obtenertodos();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Integer id){
        try {
            ClienteDTO cliente = clienteService.buscarPorId(id);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no se encontro el cliente",HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar nuevo cliente")
    @PostMapping
    public ResponseEntity<?> agregarCliente (@Valid @RequestBody Cliente cliente){
        try {
            return new ResponseEntity<>(clienteService.guardarCliente(cliente),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("no se guardo el Cliente",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Modificar campos de un cliente")
    @PatchMapping({"/{id}"})
    public ResponseEntity<?> editarCliente(@PathVariable Integer id,@RequestBody Cliente cliente){
        try {
            Cliente editado = clienteService.actualizarCliente(id, cliente);
            return new ResponseEntity<>(editado,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cliente no encontrado",HttpStatus.NOT_FOUND);
        }
    } 

    @Operation(summary = "Actualizar cliente completo")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente){
        try {
            Cliente newclient = clienteService.actualizarCliente(id, cliente);
            return new ResponseEntity<>(newclient,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cliente no encontrado",HttpStatus.NOT_FOUND);
        }
    }

}
