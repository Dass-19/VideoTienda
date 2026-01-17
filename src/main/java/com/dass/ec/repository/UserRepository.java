/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.repository;

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
public class UserRepository extends CrudRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User findByEmail(String email) {
        List<User> result = em.createNamedQuery("User.fnGetByEmail", User.class)
                            .setParameter("email", email)
                            .getResultList();
        
        return result.isEmpty() ? null : result.get(0);
    }

    public List<User> findAllCustomers() {
        return em.createQuery("SELECT u FROM User u " + "WHERE u.role.name = 'USER'", User.class).getResultList();
    }
}
