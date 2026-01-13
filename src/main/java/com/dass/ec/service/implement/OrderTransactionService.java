/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.dto.CartItemDTO;
import com.dass.ec.model.entity.Order;
import com.dass.ec.model.entity.OrderItem;
import com.dass.ec.model.entity.User;
import com.dass.ec.service.IOrderItemService;
import com.dass.ec.service.IOrderService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author H P
 */
@Stateless
public class OrderTransactionService {
    @EJB
    private IOrderService orderService;

    @EJB
    private IOrderItemService orderItemService;

    @Transactional(rollbackOn = Exception.class)
    public void purchaseProcess(User user, List<CartItemDTO> items, double total) {

        Order order = new Order();
        order.setUser(user);
        order.setDate(LocalDate.now());
        order.setTotal(total);
        orderService.guardar(order);

        for (CartItemDTO item : items) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setMovie_id(item.getMovieId());
            oi.setDate(item.getReleaseDate());
            oi.setPrice(item.getUnitPrice());
            oi.setTitle(item.getMovieTitle());
            oi.setQuantity(item.getQuantity());

            orderItemService.guardar(oi);
        }

    }
}
