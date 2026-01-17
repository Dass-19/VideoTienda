/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dass.ec.service.implement;

import com.dass.ec.dto.MovieDTO;
import com.dass.ec.service.IMovieService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.Stateless;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author H P
 */
@Stateless
public class MovieServiceImpl implements IMovieService {

    public static final String API_KEY = System.getProperty("TMDB_API_KEY");

    public static final String BASE_URL = System.getProperty("TMDB_BASE_URL");

    public static final String IMAGE_BASE = System.getProperty("TMDB_IMAGE_BASE");

    @Override
    public List<MovieDTO> getPopularMovies() {

        List<MovieDTO> movies = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + API_KEY)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            for (JsonNode node : root.get("results")) {
                MovieDTO m = new MovieDTO();
                m.setId(node.get("id").asLong());
                m.setTitle(node.get("title").asText());
                m.setOverview(node.get("overview").asText());
                m.setPosterPath(IMAGE_BASE + node.get("poster_path").asText());
                m.setReleaseDate(LocalDate.parse(node.get("release_date").asText()));
                m.setPrice(randomPrice());

                movies.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    @Override
    public double randomPrice() {
        double price = ThreadLocalRandom.current().nextDouble(5.0, 15.01);
        return Math.round(price);
    }
}
