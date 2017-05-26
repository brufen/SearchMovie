package com.movies.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.data.Movie;
import com.movies.exception.MoviesException;
import com.movies.urlreader.MovieDbUrlReader;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maja on 5/24/2017.
 * {@link JsonSimpleParser} implements {@link IJsonParser}
 */
public class JsonSimpleParser implements IJsonParser {
    private static final Logger log = Logger.getLogger(JsonSimpleParser.class);

    public JsonSimpleParser () {
    }

    @Override
    public String getActorID (JSONArray results) throws MoviesException {
        try {
            JSONObject result;
            String id = "";

            for (int i = 0; i < results.size(); i++) {
                result = (JSONObject) results.get(i);
                id = result.get("id").toString();
            }

            return id;
        } catch (Exception e) {
            String error = "Problem parsing data from json{" + results.toString() + "}";
            log.error(error, e);
            throw new MoviesException(error);
        }
    }

    //get the list of movies
    @Override
    public List<String> getMovieDetails (List<String> movieList) throws MoviesException {
        try {
            List<String> movieObjectList = new ArrayList<>();
            MovieDbUrlReader urlReader = new MovieDbUrlReader();
            JSONObject jsonObject = null;
            JSONParser jsonParser = new JSONParser();
            String movieSearch = "";
            Movie movieObject = new Movie();
            for (String movie : movieList) {
                movie = movie.replaceAll(" ", "+");
                movieSearch = "https://api.themoviedb.org/3/movie/" + movie
                        + "?api_key=64d2a074e794f43cfe1b4782eac289ff";

                Thread.sleep(3000); //Delay to comply with rate limiting of the Api
                String movieInfo = urlReader.readUrl(movieSearch);

                try {
                    jsonObject = (JSONObject) jsonParser.parse(movieInfo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String imdbId = jsonObject.get("imdb_id").toString();
                String imdbLink = "www.imdb.com/title/" + imdbId;
                movieObjectList.add(imdbLink);
                log.info("Movie link added");
            }
            return movieObjectList;
        } catch (Exception e) {
            String error = "Problem parsing data from json{" + movieList.toString() + "}";
            log.error(error, e);
            throw new MoviesException(error);
        }
    }

    @Override
    public List<String> getMovieIds (JSONArray movies) throws MoviesException {
        try {
            List<String> movieIds = new ArrayList<String>();
            JSONObject movie;

            for (int i = 0; i < movies.size(); i++) {
                movie = (JSONObject) movies.get(i);
                movieIds.add(movie.get("id").toString());
            }
            return movieIds;
        } catch (Exception e) {
            String error = "Problem parsing data from json{" + movies.toString() + "}";
            log.error(error, e);
            throw new MoviesException(error);
        }
    }

    @Override
    public List<String> getMovieTitles (JSONArray movies) throws MoviesException {
        try {
            List<String> movieList = new ArrayList<String>();
            JSONObject movie;

            for (int i = 0; i < movies.size(); i++) {
                movie = (JSONObject) movies.get(i);

                movieList.add(movie.get("title").toString());
            }
            return movieList;

        } catch (Exception e) {
            String error = "Problem parsing data from json{" + movies.toString() + "}";
            log.error(error, e);
            throw new MoviesException(error);
        }
    }

    @Override
    public List<String> getReleaseDates (JSONArray movies) throws MoviesException {
        try {

            List<String> movieList = new ArrayList<String>();
            JSONObject movie;

            for (int i = 0; i < movies.size(); i++) {
                movie = (JSONObject) movies.get(i);
                movieList.add(movie.get("release_date").toString());
            }
            return movieList;
        } catch (Exception e) {
            String error = "Problem parsing data from json{" + movies.toString() + "}";
            log.error(error, e);
            throw new MoviesException(error);
        }
    }

    @Override
    public JSONArray getCastArray (String info) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject total = (JSONObject) jsonParser.parse(info);
        JSONArray results = (JSONArray) total.get("cast");
        return results;
    }

    @Override
    public JSONArray getResults (String info) throws ParseException {
        JSONObject total = null;
        try {
            JSONParser jsonParser;
            jsonParser = new JSONParser();
            total = (JSONObject) jsonParser.parse(info);
            JSONArray results = (JSONArray) total.get("results");
            if (!results.isEmpty()) {
                System.out.println("ok");
            } else {
                results.size();
                log.error("No results for searched term...");
                System.exit(1);
            }
            return results;
        } catch (Exception e) {
            log.error("Problem parsing json{" + info + "}", e);
            return null;
        }
    }


    @Override
    public List<Movie> getBeanData (JSONArray movies) throws MoviesException, IOException {

        List<Movie> listMovies = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            JSONObject singleMovie = (JSONObject) movies.get(i);
            ObjectMapper mapper = new ObjectMapper();
            Movie singleMovieBean = mapper.readValue(singleMovie.toString(), Movie.class);
            listMovies.add(singleMovieBean);
        }
        return listMovies;

    }

}

