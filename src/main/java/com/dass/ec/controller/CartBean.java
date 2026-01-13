/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.dto.CartItemDTO;
import com.dass.ec.dto.MovieDTO;
import com.dass.ec.model.entity.User;
import com.dass.ec.service.IOrderItemService;
import com.dass.ec.service.IOrderService;
import com.dass.ec.service.implement.OrderTransactionService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

/**
 *
 * @author H P
 */
@Named(value = "cartBean")
@SessionScoped
@Getter
@Setter
public class CartBean implements Serializable {

    @EJB
    private IOrderService orderService;

    @EJB
    private IOrderItemService orderItemService;

    @EJB
    private OrderTransactionService transactionService;

    @Inject
    private LoginBean loginBean;

    private List<CartItemDTO> items = new ArrayList<>();

    public void addToCart(MovieDTO movie, int quantity) {
        for (CartItemDTO item : items) {
            if (item.getMovieId().equals(movie.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        CartItemDTO newItem = new CartItemDTO();
        newItem.setMovieId(movie.getId());
        newItem.setMovieTitle(movie.getTitle());
        newItem.setUnitPrice(movie.getPrice());
        newItem.setQuantity(quantity);
        newItem.setReleaseDate(movie.getReleaseDate());
        items.add(newItem);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", movie.getTitle() + " agregado al carrito"));
        PrimeFaces.current().ajax().update(":menuForm", ":formCarrito");
    }

    public void removeFromCart(Long movieId) {
        items.removeIf(item -> item.getMovieId().equals(movieId));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Película eliminada del carrito"));
        PrimeFaces.current().ajax().update(":menuForm", ":formCarrito");
    }

    public void updateQuantity(Long movieId, int newQuantity) {
        for (CartItemDTO item : items) {
            if (item.getMovieId().equals(movieId)) {
                if (newQuantity <= 0) {
                    items.remove(item);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Película eliminada del carrito"));
                } else {
                    item.setQuantity(newQuantity);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Cantidad actualizada"));
                }
                PrimeFaces.current().ajax().update(":menuForm", ":formCarrito");
                return;
            }
        }
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public int getTotalItems() {
        return items.stream().mapToInt(CartItemDTO::getQuantity).sum();
    }

    public double getTotal() {
        return items.stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
    }

    public void clear() {
        items.clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carrito vaciado", "Se eliminaron todos los productos"));
        PrimeFaces.current().ajax().update(":menuForm", ":formCarrito");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void completePurchase() {
        if (items.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Carrito vacío", "No hay productos para comprar"));
            return;
        }

        User user = loginBean.getUser();
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sesión requerida", "Debe iniciar sesión para comprar"));
            return;
        }

        double total = getTotal();
        try {
            transactionService.purchaseProcess(user, items, total);
            clear();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra exitosa", String.format("Total: $%.2f", total)));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo completar la compra"));
        }
        PrimeFaces.current().ajax().update(":menuForm", ":formCarrito");
    }
}
