/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author H P
 */
@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "FK_order_id")
    )
    private Order order;

    @Column(name = "movie_id", nullable = false)
    private Long movie_id;
    
    @Column(name = "release_date", nullable = false)
    private LocalDate date;

    @Column(name = "unit_price", nullable = false)
    private double price;

    @Column(name = "movie_title", nullable = false)
    private String title;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
