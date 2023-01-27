package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> directorMovieMap; //Pair is Director Name,List of movie names

    //Initialization(Important)
    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        this.directorMovieMap = new HashMap<String,List<String>>();
    }

    //1.Add Movie
    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    //2.Add Director
    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    //3.add Pair an existing movie and director
    public void updatePair(String movie,String director){

        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currentMoviesByDirector = new ArrayList<>();

            if(directorMovieMap.containsKey(director)){
                currentMoviesByDirector = directorMovieMap.get(director);
            }

            currentMoviesByDirector.add(movie);

            directorMovieMap.put(director,currentMoviesByDirector);

        }
    }

    //4.Get MovieName
    public Movie get_Movie(String movieName){
            return movieMap.get(movieName);
    }

    //5.Get Director
    public Director find_Director(String directorName){
        return directorMap.get(directorName);
    }

    //6.Get List of movies name for a given director name
    public List<String> get_Director(String directorName){
        List<String> moviesList = new ArrayList<>();

        if(directorMovieMap.containsKey(directorName)){
            moviesList = directorMovieMap.get(directorName);
        }
        return moviesList;
    }

    //7.Get List of all movies added
    public List<String> get_allMovies(){
        return new ArrayList<>(movieMap.keySet());
    }


    //8.Delete a director and its movies from the records
    public void delete_director_movie(String directorName){

        List<String> currentMovieList = new ArrayList<>();

        if(directorMovieMap.containsKey(directorName)){

            //1.find the movieName by director from the pair
            currentMovieList = directorMovieMap.get(directorName);

            //2.Delete all movies from movieMap by using movieName
            for (String movie : currentMovieList){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            //3.Delete the Pair
            directorMovieMap.remove(directorName);
        }

        //4.delete director from sirectoMap usinf DirectorName
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }

    }


    //9.Delete all directors and all movies by them from the records
    //(Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
    public void deleteAllDirector(){

        HashSet<String> moviesList = new HashSet<>();

        //1.Deleting directorMap
        directorMap = new HashMap<>();

        //2.Find all the movies by All the directors combined
        for(String directorName : directorMovieMap.keySet()){

            //3.Iterating in the list of movies by adirector and add into a hashSet(moviesList)
            for(String movie : directorMovieMap.get(directorName)){
                moviesList.add(movie);
            }
        }

        //4.deleting the movies from movieMap
        for(String movie : moviesList){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }

        //5.clearing the pair
        directorMovieMap = new HashMap<>();

    }



}
