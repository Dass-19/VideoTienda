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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author H P
 */
@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;
    
    @Column(name="name", nullable=false)
    private String name;
    
    @Column(name="email", nullable=false, unique=true)
    private String email;
    
    @Column(name="password", nullable=false)
    private String password;
    
    @ManyToOne(optional=false)
    @JoinColumn(
        name="id_rol",
        foreignKey=@ForeignKey(name="FK_user_role")
    )
    private Role role;
    
    @Column(name="active", nullable=false)
    private boolean active;
}
