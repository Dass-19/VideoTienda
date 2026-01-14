/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.repository;

import com.dass.ec.model.entity.OrderItem;
import com.dass.ec.repository.persistence.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;

/**
 *
 * @author H P
 */
@Stateless
public class OrderItemRepository extends CrudRepository<OrderItem>{

    public OrderItemRepository() {
        super(OrderItem.class);
    } 

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
}
