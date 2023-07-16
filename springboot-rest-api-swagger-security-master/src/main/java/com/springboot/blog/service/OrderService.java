package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return mapOrdersToDTOs(orders);
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        return mapOrderToDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return mapOrderToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        existingOrder.setCustomerId(orderDTO.getCustomerId());
        existingOrder.setOrderAt(orderDTO.getOrderAt());

        Order updatedOrder = orderRepository.save(existingOrder);
        return mapOrderToDTO(updatedOrder);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    // Helper methods for mapping between DTOs and entities
    private OrderDTO mapOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setOrderAt(order.getOrderAt());
        return orderDTO;
    }

    private List<OrderDTO> mapOrdersToDTOs(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderToDTO)
                .collect(Collectors.toList());
    }

    private Order mapDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setOrderAt(orderDTO.getOrderAt());
        return order;
    }
}