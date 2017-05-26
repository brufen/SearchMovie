package com.movies.start;

import com.movies.data.Movie;
import com.movies.data.ResultData;
import com.movies.exception.MoviesException;
import com.movies.io.MoviesFileWriter;
import com.movies.parser.IJsonParser;
import com.movies.parser.JsonSimpleParser;
import com.movies.service.IMovieService;
import com.movies.service.MovieDbService;
import com.movies.urlreader.MovieDbUrlReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Maja
 */
public class StartApp {

    private static final Logger log = Logger.getLogger(StartApp.class);
    private static final IJsonParser jsonParser = new JsonSimpleParser();
    private static final IMovieService movieService = new MovieDbService(new MovieDbUrlReader());

    public static void main (String[] args) throws ParseException, MoviesException {

        BasicConfigurator.configure();
        log.info("Application started....");
        Scanner scanner = new Scanner(System.in);
        JSONArray id = null;
        String searchString;
        List<String> movieIds = new ArrayList<>();
        List<String> movieTitles = new ArrayList<>();
        List<String> movieDates = new ArrayList<>();
        List<Movie> moiveList = new ArrayList<>();
        ResultData resultData = new ResultData();
        System.out.println("Enter an actors name: ");
        searchString = scanner.nextLine();

        String actorURL = movieService.getQueryString(searchString);
        String actorInfo = movieService.getActorInfo(actorURL);
        id = jsonParser.getResults(actorInfo);

        while (id.equals("")) {
            if (checkActor(actorInfo)) {
                id = jsonParser.getResults(actorInfo);
            } else {

                System.out.println("Either the actors name is wrong or does not exist");
                System.out.println("Enter an actors name: " + " (q for Quit)");
                searchString = scanner.nextLine();
                if (searchString.equals("q"))
                    System.out.println("Goodbye!");
                System.exit(0);
            }
            actorURL = movieService.getQueryString(searchString);
            actorInfo = movieService.getActorInfo(actorURL);

        }


        String CastInfo = jsonParser.getActorID(id);

        String castUrl = movieService.getCatsInfo(CastInfo);

        String movieCast = movieService.getActorInfo(castUrl);// returns json with actors list of movies
        movieIds = jsonParser.getCastArray(movieCast); // list of all the movies

        List<String> results = new ArrayList<>();
        try {
            moiveList = jsonParser.getBeanData(jsonParser.getCastArray(movieCast));
            for (Movie movie : moiveList) {
                results.add(movie.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> resultIDs = new ArrayList<>();
        for (Movie movie : moiveList) {
            resultIDs.add(movie.getId());
        }
        List<String> resultImdbId = jsonParser.getMovieDetails(resultIDs);
        HashMap<Movie, String> map = new HashMap<>();
        map = ResultData.getResultData(moiveList, resultImdbId);
        String fileName = "sample.csv";
        String result = map.toString();
        MoviesFileWriter mr = new MoviesFileWriter();
        try {
            mr.writeToFile(result, fileName);
            log.info("Result data for searched term: " + searchString + " are saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("Application ended");


    }

    public static boolean checkActor (String actorInfo) throws ParseException {
        boolean isValid = false;
        if (!jsonParser.getResults(actorInfo).isEmpty()) {
            isValid = true;

        }
        return isValid;

    }

}