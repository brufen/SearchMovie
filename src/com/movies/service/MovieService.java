package com.movies.service;

import com.movies.urlreader.UrlReader;
import org.apache.log4j.Logger;

/**
 * {@link MovieService} has methods to get all of the data.
 * Created by Maja
 */
public abstract class MovieService implements IMovieService {
    private static final Logger log = Logger.getLogger(MovieService.class);
    private final String apiKey;
    private final String apiUrl;
    private final UrlReader urlReader;

    protected MovieService (String apiKey, String apiUrl, UrlReader urlReader) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.urlReader = urlReader;
    }

    public String getQueryString (String query) {

        try {
            String actorUrl = urlReader.getSearchURL(query, apiUrl, apiKey);
            log.info("Generated url have response " + actorUrl);
            return actorUrl;
        } catch (Exception e) {
            log.error("Problem getting the results for search term {" + query + "}", e);
            return "";
        }
    }

    public String getCatsInfo (String id) {
        String movieId = id;
        String response = "";
        try {
            log.info("Movie info data: " + response);
            return response = urlReader.getMovieListURL(movieId, apiKey);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Problem getting the results for movie ID {" + movieId + "}", e);
        }
        return "";

    }

    public String getMovieURL (String id) {
        String movieId = id;
        String response = "";
        try {
            return response = urlReader.getMovieListURL(movieId, apiKey);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Problem getting the results for movie ID {" + movieId + "}", e);
        }
        return "";

    }

    public String getActorInfo (String url) {
        String actorInfo = "";
        try {
            return actorInfo = urlReader.readUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Problem getting the results for the url {" + url + "}", e);
        }
        return "";

    }

}





