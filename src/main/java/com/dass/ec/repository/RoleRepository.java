/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.repository;

import com.dass.ec.model.entity.Role;
import com.dass.ec.repository.persistence.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class RoleRepository extends CrudRepository<Role> {

    public RoleRepository() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Role findByName(String name) {
        return em.createQuery("SELECT r FROM Role r WHERE r.name = :name",Role.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
