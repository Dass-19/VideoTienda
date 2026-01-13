/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.model.entity.User;
import com.dass.ec.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author H P
 */
@Stateless
public class AuthService {

    @EJB
    private UserRepository userRepository;

    public User authenticate(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
    public boolean hasRole(User user, String roleName) {
        if (user == null || user.getRole() == null) {
            return false;
        }
        return user.getRole().getName().equalsIgnoreCase(roleName);
    }
}
