package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.Product;
import com.mayan.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(product -> new ProductDTO(product));
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        Product result = repository.findById(id).get();
        return new ProductDTO(result);
    }
}
