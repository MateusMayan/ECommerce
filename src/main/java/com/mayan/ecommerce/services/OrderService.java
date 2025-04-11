package com.mayan.ecommerce.services;

import com.mayan.ecommerce.dtos.OrderDTO;
import com.mayan.ecommerce.dtos.OrderItemDTO;
import com.mayan.ecommerce.dtos.ProductDTO;
import com.mayan.ecommerce.entities.*;
import com.mayan.ecommerce.repositories.OrderItemRepository;
import com.mayan.ecommerce.repositories.OrderRepository;
import com.mayan.ecommerce.repositories.ProductRepository;
import com.mayan.ecommerce.services.exceptions.ForbiddenException;
import com.mayan.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado.")
        );
        authService.validateSelfOrAdmin(result.getClient().getId());
        return new OrderDTO(result);
    }

    @Transactional
    public OrderDTO create(OrderDTO dto) {
        Order entity = new Order();
        entity.setMoment(Instant.now());
        entity.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        entity.setClient(user);

        for (OrderItemDTO orderItemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());
            OrderItem orderItem = new OrderItem(entity, product, orderItemDTO.getQuantity(), product.getPrice());
            entity.getItems().add(orderItem);
        }
        entity = repository.save(entity);
        orderItemRepository.saveAll(entity.getItems());
        return new OrderDTO(entity);
    }
}
