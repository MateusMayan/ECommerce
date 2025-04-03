package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.Product;
import com.mayan.ecommerce.repositories.ProductRepository;
import com.mayan.ecommerce.services.exceptions.DatabaseException;
import com.mayan.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(product -> new ProductDTO(product));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado.")
        );
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
        try {
        Product result = repository.getReferenceById(id);
        copyDtoToEntity(dto, result);
        result = repository.save(result);
        return new ProductDTO(result);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Produto não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado.");
        } try {
        repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
