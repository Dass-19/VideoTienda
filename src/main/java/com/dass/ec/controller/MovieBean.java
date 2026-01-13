/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.controller;

import com.dass.ec.dto.MovieDTO;
import com.dass.ec.service.IMovieService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author H P
 */
@Named(value="catalogBean")
@ViewScoped
@Getter
@Setter
public class MovieBean implements Serializable{
    @EJB
    private IMovieService movieService;
    
    private List<MovieDTO> movies;
    
    @PostConstruct
    public void init() {
        movies = movieService.getPopularMovies();
    }
    
    public List<MovieDTO> getMovies() {
        return movies;
    }
}
