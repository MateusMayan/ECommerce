package com.mayan.ecommerce.repositories;

import com.mayan.ecommerce.entities.Order;
import com.mayan.ecommerce.entities.OrderItem;
import com.mayan.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
