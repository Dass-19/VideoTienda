/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.model.entity.User;
import com.dass.ec.service.IUserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

/**
 *
 * @author H P
 */
@Named(value="registerBean")
@RequestScoped
@Getter
@Setter
public class RegisterBean implements Serializable{
    @EJB
    private IUserService usuarioService;
    
    private User user = new User();
    
    public void register(){
        usuarioService.guardar(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta creada. Inicia sesión!"));
        PrimeFaces.current().executeScript("PF('dlgRegistro').hide()");
    }
}
