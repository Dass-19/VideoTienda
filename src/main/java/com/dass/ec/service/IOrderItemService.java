/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dass.ec.service;

import com.dass.ec.model.entity.OrderItem;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author H P
 */
@Local
public interface IOrderItemService {
    OrderItem guardar(OrderItem orderItem);
    
    OrderItem editar(OrderItem orderItem);
    
    void eliminar(OrderItem orderItem);
    
    List<OrderItem> listar();
}
