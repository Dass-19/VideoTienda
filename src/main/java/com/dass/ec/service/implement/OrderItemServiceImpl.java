/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.model.entity.OrderItem;
import com.dass.ec.repository.OrderItemRepository;
import com.dass.ec.service.IOrderItemService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class OrderItemServiceImpl implements IOrderItemService{

    @EJB
    private OrderItemRepository orderItemRepository;
    
    @Override
    public OrderItem guardar(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem editar(OrderItem orderItem) {
        return orderItemRepository.update(orderItem);
    }

    @Override
    public void eliminar(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }

    @Override
    public List<OrderItem> listar() {
        return orderItemRepository.findAll();
    }
    
}
