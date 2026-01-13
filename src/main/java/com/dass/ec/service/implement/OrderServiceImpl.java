/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.model.entity.Order;
import com.dass.ec.model.entity.User;
import com.dass.ec.repository.OrderRepository;
import com.dass.ec.service.IOrderService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class OrderServiceImpl implements IOrderService{
    @EJB
    private OrderRepository orderRepository;

    
    @Override
    public Order guardar(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order editar(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public void eliminar(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> listar() {
        return orderRepository.findAll();
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
