package com.movies.urlreader;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * {@link MovieDbUrlReader} Connects to api.themoviedb.org and gets the data via readUrl method.
 * <p>
 * Created by Maja
 */
public class MovieDbUrlReader implements UrlReader {
    private static final Logger log = Logger.getLogger(MovieDbUrlReader.class);

    @Override
    public String readUrl (String targetURL) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            //set agent
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setUseCaches(true);
            connection.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //Get Response
            InputStream is = connection.getInputStream();

            int statusCode = connection.getResponseCode();
            switch (statusCode) {
                case 400:
                    return "Error 400 - Bad request.";
                case 401:
                    return "Error 401 - Unauthorized request.";

            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                response.append(inputLine);

            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            log.error("Not possible to read url from readUrl {" + targetURL + "}", e);
            return "{}";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    @Override
    public String getSearchURL (String searchString, String apiUrl, String apiKey) throws Exception {
        String field = "&query=";
        String targetURL = apiUrl + "/3/search/person?api_key=" + apiKey + field + URLEncoder.encode(searchString, "UTF-8");
        targetURL.concat(searchString);
        log.info("Generated URL for: " + targetURL);
        return targetURL;
    }
    @Override
    public String getMovieURL (String id, String apiKey) throws Exception {
        String targetURL = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey;
        targetURL.concat(id);
        log.info("Movie URL: " + targetURL);
        return targetURL;
    }
    @Override
    public String getMovieListURL (String id, String apiKey) throws Exception {
        String targetURL = "https://api.themoviedb.org/3/person/" + id + "/movie_credits?api_key=" + apiKey;
        targetURL.concat(id);
        log.info("Actor movie details URL: " + targetURL);
        return targetURL;
    }


}
