/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.dto;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO implements Serializable{
    private Long movieId;
   
    private String movieTitle;
    
    private LocalDate releaseDate;
    
    private double unitPrice;
    
    private int quantity;
}
