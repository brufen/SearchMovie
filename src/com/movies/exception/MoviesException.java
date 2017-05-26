package com.movies.exception;

/**
 * {@link MoviesException} Custom exception
 * Created by Maja
 */
public class MoviesException extends Exception {

    private static final long serialVersionUID = -4844903765740173184L;

    public MoviesException (String message) {
        super(message);
    }
}
