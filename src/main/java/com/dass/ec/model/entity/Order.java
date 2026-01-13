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
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(
        name="user_id",
        foreignKey=@ForeignKey(name="FK_user_id")
    )
    private User user;
    
    @Column(name="order_date", nullable=false)
    private LocalDate date;
    
    @Column(name="total", nullable=false)
    private double total;
}
