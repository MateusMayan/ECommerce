package com.mayan.ecommerce.controllers;

import com.mayan.ecommerce.dtos.CategoryDTO;
import com.mayan.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        List<CategoryDTO> result = service.findAll();
        return ResponseEntity.ok(result);
    }
}
