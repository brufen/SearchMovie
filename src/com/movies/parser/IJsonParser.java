package com.movies.parser;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.movies.data.Movie;
import com.movies.exception.MoviesException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * {@link IJsonParser} The Interface IJsonParser.
 * <p>
 * Created by Maja
 */
public interface IJsonParser {

    /**
     * getActorID is extracting id of the searched name (actor).
     *
     * @param results
     * @return
     * @throws MoviesException
     */
    public String getActorID (JSONArray results) throws MoviesException;

    /**
     * getMovieDetails For a given list of actor's movies of movies it returns
     * list of Movies
     *
     * @param movieList
     * @return
     * @throws MoviesException
     */
    public List<String> getMovieDetails (List<String> movieList) throws MoviesException;

    /**
     * getMovieIds returns list of id's of an actor.
     *
     * @param movies
     * @return
     * @throws MoviesException
     */
    public List<String> getMovieIds (JSONArray movies) throws MoviesException;

    /**
     * getMovieTitles for a given string of movie details, it returns movie titles
     *
     * @param movies
     * @return
     * @throws MoviesException
     */
    public List<String> getMovieTitles (JSONArray movies) throws MoviesException;

    ;

    /**
     * getReleaseDates for a given string of movie details, it returns movie release dates.
     *
     * @param movies
     * @return
     * @throws MoviesException
     */
    public List<String> getReleaseDates (JSONArray movies) throws MoviesException;

    /**
     * Gets json data from actor's details page.
     *
     * @param info
     * @return
     * @throws ParseException
     */
    public JSONArray getCastArray (String info) throws ParseException;

    /**
     * Gets the json data.
     *
     * @param info
     * @return
     * @throws ParseException
     */
    public JSONArray getResults (String info) throws ParseException;

    /**
     * Map json to bean.
     *
     * @param movies
     * @return
     * @throws MoviesException
     * @throws IOException
     * @throws JsonMappingException
     */
    public List<Movie> getBeanData (JSONArray movies) throws MoviesException, IOException, JsonMappingException;

}
