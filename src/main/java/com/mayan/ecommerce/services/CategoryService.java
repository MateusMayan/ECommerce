package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.CategoryDTO;
import com.mayan.ecommerce.entities.Category;
import com.mayan.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(CategoryDTO::new).toList();
    }
}
