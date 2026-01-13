/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.repository;

import com.dass.ec.model.entity.Order;
import com.dass.ec.model.entity.User;
import com.dass.ec.repository.persistence.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class OrderRepository extends CrudRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Order> findByUser(User user) {
        return em.createQuery("SELECT o FROM Order o WHERE o.user = :user", Order.class)
                .setParameter("user", user)
                .getResultList();
    }

}
