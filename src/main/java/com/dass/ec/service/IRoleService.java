/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dass.ec.service;

import com.dass.ec.model.entity.Role;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author H P
 */
@Local
public interface IRoleService {
    Role findById(Integer id);
    
    Role findByName(String name);
    
    List<Role> listar();
}
