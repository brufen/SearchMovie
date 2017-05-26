package com.movies.service;

/**
 * {@link IMovieService} has methods to perform all the main data
 * preocessing tasks.
 * Created by Maja
 */
public interface IMovieService {
    /**
     * Takes the search term and builds and url.
     *
     * @param query
     * @return
     */
    public String getQueryString (String query);

    /**
     * Gets url for the movie details
     *
     * @param id
     * @return
     */
    public String getMovieURL (String id);

    /**
     * Gets url with actors info page which contains basic info about his movies
     *
     * @param id
     * @return
     */
    public String getCatsInfo (String id);

    /**
     * Actor's basic info page
     *
     * @param url
     * @return
     */
    public String getActorInfo (String url);

}
