package com.mayan.ecommerce.controllers;

import com.mayan.ecommerce.dtos.OrderDTO;
import com.mayan.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
            OrderDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto){
        dto = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
