package com.example.store.service;

import com.example.store.dto.OrderDTO;
import com.example.store.mapper.OrderMapper;
import com.example.store.model.Order;
import com.example.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.ordersToOrderDTOs(orders);
    }
    
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order newOrder = orderMapper.orderDTOToOrder(orderDTO);
        orderRepository.save(newOrder);
        return orderMapper.orderToOrderDTO(newOrder);
    }
    
}
