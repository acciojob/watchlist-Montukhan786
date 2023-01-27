package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    //1
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie Added Successfully", HttpStatus.CREATED);
    }

    //2
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.saveDirector(director);
        return new ResponseEntity<>("New director Added Successfully", HttpStatus.CREATED);
    }

    //3
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@PathParam("movie") String movie,@PathParam("director") String director){
        movieService.createMovieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie-director pair added successfully",HttpStatus.CREATED);
    }

    //4
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie = movieService.findMovie(name);
       return new ResponseEntity<>(movie,HttpStatus.CREATED);
    }

    //5
    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.find_Director(name);
        return new ResponseEntity<>(director,HttpStatus.CREATED);
    }

    //6
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String name){
        List<String> movies = movieService.findMoviesFromDirector(name);
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }

    //7
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.find_allMovies();
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }

    //8
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
        movieService.delete_director(name);
        return new ResponseEntity<>(name + "removed succussfully",HttpStatus.CREATED);
    }

    //9
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirector();
        return new ResponseEntity<>("All Director deleted succussfully",HttpStatus.CREATED);
    }


}
