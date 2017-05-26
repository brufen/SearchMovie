package com.movies.urlreader;

/**
 * {@link UrlReader} has methods to connect and stream data from api.themoviedb.org
 * Created by Maja
 */
public interface UrlReader {
    /**
     * Builds an url for the search term. Page with basic data about actor.
     *
     * @param searchString
     * @param apiUrl
     * @param apiKey
     * @return
     * @throws Exception
     */
    public String getSearchURL (String searchString, String apiUrl, String apiKey) throws Exception;

    /**
     * Connect with api and reads the data. If no data, it ends an app.
     *
     * @param url
     * @return
     */
    public String readUrl (String url);

    /**
     * Builds the url for the detailed actor page.
     *
     * @param id
     * @param apiUrl
     * @return
     * @throws Exception
     */
    public String getMovieListURL (String id, String apiUrl) throws Exception;

    /**
     * Builds an url for the specific movie details.
     *
     * @param id
     * @param apiKey
     * @return
     * @throws Exception
     */
    public String getMovieURL (String id, String apiKey) throws Exception;
}
