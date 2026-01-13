/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.security;

import com.dass.ec.controller.LoginBean;
import jakarta.servlet.Filter;
import jakarta.inject.Inject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author H P
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter{

    @Inject
    private LoginBean loginBean;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (loginBean == null || !loginBean.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }
        chain.doFilter(req, res);
    }
}
