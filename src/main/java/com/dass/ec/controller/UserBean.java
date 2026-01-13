/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.model.entity.User;
import com.dass.ec.service.IUserService;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

/**
 *
 * @author H P
 */
@Getter
@Setter
@Named(value="userBean")
@ViewScoped
public class UserBean implements Serializable{
    @EJB
    private IUserService usuarioService;
    
    private User user;
    
    private List<User> usuarios;
    
    public void nuevo(){
        user = new User();
    }
    
    public List<User> getUsuarios(){
        return usuarioService.listar();
    }
    
}
