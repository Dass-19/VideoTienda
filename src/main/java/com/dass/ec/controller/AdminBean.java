/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.model.entity.Order;
import com.dass.ec.model.entity.User;
import com.dass.ec.service.IOrderService;
import com.dass.ec.service.IUserService;
import jakarta.annotation.PostConstruct;
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
@Named(value = "adminBean")
@ViewScoped
@Getter
@Setter
public class AdminBean implements Serializable {

    @EJB
    private IOrderService orderService;

    @EJB
    private IUserService userService;

    private List<User> users;
    private User selectedUser;
    private List<Order> selectedUserOrders;
    private User userToEdit;

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        users = userService.listarClientes();
    }

    public void prepareEditUser(User user) {
        this.userToEdit = user;
    }

    public void updateUser() {
        try {
            userService.editar(userToEdit);
            loadUsers();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado correctamente"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el usuario"));
        }
    }

    public void toggleUserStatus(User user) {
        try {
            user.setActive(!user.isActive());
            userService.editar(user);
            loadUsers();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado actualizado", user.getName() + " ahora está " + (user.isActive() ? "activo" : "inactivo")));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "No se pudo cambiar el estado del usuario"));
        }
    }

    public void loadUserOrders(User user) {
        selectedUser = user;
        selectedUserOrders = orderService.findByUser(user);
    }

    public long getTotalTransactions() {
        return orderService.listar().size();
    }

    public double getTotalRevenue() {
        return orderService.listar().stream().mapToDouble(Order::getTotal).sum();
    }

    public long getUserOrderCount(User user) {
        return orderService.findByUser(user).size();
    }

    public double getUserTotalSpent(User user) {
        return orderService.findByUser(user).stream().mapToDouble(Order::getTotal).sum();
    }
}
