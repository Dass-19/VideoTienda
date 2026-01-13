/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dass.ec.service;

import com.dass.ec.model.entity.Order;
import com.dass.ec.model.entity.User;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author H P
 */
@Local
public interface IOrderService {
    Order guardar(Order order);
    
    Order editar(Order order);
    
    void eliminar(Order order);
    
    List<Order> listar();
    
    List<Order> findByUser(User user);
}
