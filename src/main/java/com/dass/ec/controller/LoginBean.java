/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.model.entity.User;
import com.dass.ec.service.implement.AuthService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
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
@Getter
@Setter
@Named(value="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    @EJB
    private AuthService authService;

    private User user;

    private String email;

    private String password;

    public String login() {

        user = authService.authenticate(email, password);

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credenciales incorrectas"));
            return null;
        }

        if (authService.hasRole(user, "ADMIN")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bienvenido al dashboard"));
            return "/admin/dashboard.xhtml?faces-redirect=true";
        }
        return "/user/home.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isAdmin() {
        return isLoggedIn() && authService.hasRole(user, "ADMIN");
    }

    public boolean isUser() {
        return isLoggedIn() && authService.hasRole(user, "USER");
    }
}
