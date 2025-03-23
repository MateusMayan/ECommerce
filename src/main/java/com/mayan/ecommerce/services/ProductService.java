package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.Product;
import com.mayan.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> result = repository.findAll();
        return result.stream().map(x -> new ProductDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        Product result = repository.findById(id).get();
        return new ProductDTO(result);
    }
}
