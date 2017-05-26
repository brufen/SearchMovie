package com.movies.parser;

import com.movies.exception.MoviesException;
import com.movies.io.MoviesFileWriter;
import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Maja
 */
public class JasonSimpleParserTese {
    private final MoviesFileWriter fileWriter = new MoviesFileWriter();
    private static final IJsonParser jsonParser = new JsonSimpleParser();

    @BeforeClass
    public static void init () {
        BasicConfigurator.configure();
    }


    @Test(expected = MoviesException.class)
    public void testThatThereIsNoResultsForRubbishInput () throws MoviesException {
        List<String> testList = new ArrayList<>();
        testList.add("   ");
        jsonParser.getMovieDetails(testList);

    }

}
