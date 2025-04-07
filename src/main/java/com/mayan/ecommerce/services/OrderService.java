package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.OrderDTO;
import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.Order;
import com.mayan.ecommerce.entities.Product;
import com.mayan.ecommerce.repositories.OrderRepository;
import com.mayan.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado.")
        );
        return new OrderDTO(result);
    }
}
