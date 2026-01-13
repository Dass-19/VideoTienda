/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dass.ec.service;

import com.dass.ec.model.entity.User;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author H P
 */
@Local
public interface IUserService {
    User guardar(User usuario);
    
    User editar(User usuario);
    
    void eliminar(User usuario);
    
    List<User> listar();
    
    List<User> listarClientes();
}
