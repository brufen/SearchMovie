package com.movies.service;

import com.movies.parser.IJsonParser;
import com.movies.parser.JsonSimpleParser;
import com.movies.urlreader.MovieDbUrlReader;
import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maja
 */
public class MovieDbServiceTest {

    IJsonParser jsonParser = new JsonSimpleParser();
    IMovieService movieService = new MovieDbService(new MovieDbUrlReader());

    @BeforeClass
    public static void before () {
        BasicConfigurator.configure();
    }

    @Test
    public void MovieDbService () {
        assertEquals("Problem getting the results for movie ID aaaa", movieService.getCatsInfo("aaaa"));
        assertEquals("Problem getting the results for movie ID jedan", movieService.getMovieURL("jedan"));

    }


}
