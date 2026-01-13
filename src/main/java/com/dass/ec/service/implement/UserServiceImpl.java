/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.model.entity.Role;
import com.dass.ec.model.entity.User;
import com.dass.ec.repository.RoleRepository;
import com.dass.ec.repository.UserRepository;
import com.dass.ec.service.IUserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class UserServiceImpl implements IUserService{
    
    @EJB
    private UserRepository usuarioRepository;
    
    @EJB
    private RoleRepository roleRepository;
    
    @Override
    public User guardar(User usuario) {
        Role role = roleRepository.findByName("USER");
        usuario.setRole(role);
        usuario.setActive(true);
        return usuarioRepository.save(usuario);
    }

    @Override
    public User editar(User usuario) {
        return usuarioRepository.update(usuario);
    }
    
    @Override
    public void eliminar(User usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<User> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<User> listarClientes() {
        return usuarioRepository.findAllCustomers();
    }
    
    
    
}