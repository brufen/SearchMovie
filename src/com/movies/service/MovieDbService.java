package com.movies.service;

import com.movies.urlreader.UrlReader;

/**
 * {@link MovieService} service extendes abstract class {@link MovieService} , constructor provides key and url.
 * Created by Maja
 */
public class MovieDbService extends MovieService implements IMovieService {
    // api key 1: 382a81cb81a8ab80eb5f89325e2095d3
    // api key 2: 64d2a074e794f43cfe1b4782eac289ff
    public MovieDbService (UrlReader urlReader) {
        super("64d2a074e794f43cfe1b4782eac289ff",
                "https://api.themoviedb.org/", urlReader);

    }


}
