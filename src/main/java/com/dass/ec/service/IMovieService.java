/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dass.ec.service;

import com.dass.ec.dto.MovieDTO;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author H P
 */
@Local
public interface IMovieService {
    
    List<MovieDTO> getPopularMovies();
    
    double randomPrice();

}
