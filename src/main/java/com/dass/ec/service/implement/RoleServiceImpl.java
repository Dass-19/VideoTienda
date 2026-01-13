/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.model.entity.Role;
import com.dass.ec.repository.RoleRepository;
import com.dass.ec.service.IRoleService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class RoleServiceImpl implements IRoleService{
    @EJB
    private RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> listar() {
        return roleRepository.findAll();
    }
    
    
}
