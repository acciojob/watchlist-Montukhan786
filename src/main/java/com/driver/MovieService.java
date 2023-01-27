package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    //1.Add Movie
    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }

    //2.Add Director
    public void saveDirector(Director director){
        movieRepository.saveDirector(director);
    }

    //3.add Pair an existing movie and director
    public void createMovieDirectorPair(String movie,String director){
        movieRepository.updatePair(movie,director);
    }

    //4.Get MovieName
    public Movie findMovie(String name){
        return  movieRepository.get_Movie(name);
    }

    //5.Get Director
    public Director find_Director(String directorName){
        return movieRepository.find_Director(directorName);
    }

    //6.Get List of movies name for a given director name
    public List<String> findMoviesFromDirector(String directorName){
        return movieRepository.get_Director(directorName);
    }

    //7.Get List of all movies added
    public List<String> find_allMovies(){
        return movieRepository.get_allMovies();
    }

    //8.Delete a director and its movies from the records
    public void delete_director(String directorName){
        movieRepository.delete_director_movie(directorName);
    }

    //9.Delete all directors and all movies by them from the records
    //(Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    public void deleteAllDirector(){
        movieRepository.deleteAllDirector();
    }

}
