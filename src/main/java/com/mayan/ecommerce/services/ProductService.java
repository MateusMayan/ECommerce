package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.Product;
import com.mayan.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public ProductDTO findById(Long id) {
        Product result = repository.findById(id).get();
        return new ProductDTO(result);
    }

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        Product result = new Product();
        copyDtoToEntity(dto, result);
        result = repository.save(result);
        return new ProductDTO(result);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product result = repository.getReferenceById(id);
        copyDtoToEntity(dto, result);
        result = repository.save(result);
        return new ProductDTO(result);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
