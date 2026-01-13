/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.repository;

import com.dass.ec.model.entity.User;
import com.dass.ec.repository.persistence.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
        return em.createQuery(
                "SELECT u FROM User u JOIN FETCH u.role "
                + "WHERE u.email = :email AND u.active = true",
                User.class
        )
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<User> findAllCustomers() {
        return em.createQuery("SELECT u FROM User u " + "WHERE u.role.name = 'USER'",User.class).getResultList();
    }
}
