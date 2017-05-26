package com.movies.data;

/**
 * Created by Maja
 * {@link Movie}  Movie pojo
 */
public class Movie {


    private String character;
    private String original_title;
    private String credit_id;
    private String release_date;
    private String id;
    private String adult;
    private String title;
    private String poster_path;

    public Movie () {
    }

    public Movie (String character, String original_title, String credit_id, String release_date, String id, String adult, String title, String poster_path) {
        this.character = character;
        this.original_title = original_title;
        this.credit_id = credit_id;
        this.release_date = release_date;
        this.id = id;
        this.adult = adult;
        this.title = title;
        this.poster_path = poster_path;
    }

    public String getCharacter () {
        return character;
    }

    public void setCharacter (String character) {
        this.character = character;
    }

    public String getOriginal_title () {
        return original_title;
    }

    public void setOriginal_title (String original_title) {
        this.original_title = original_title;
    }

    public String getCredit_id () {
        return credit_id;
    }

    public void setCredit_id (String credit_id) {
        this.credit_id = credit_id;
    }

    public String getRelease_date () {
        return release_date;
    }

    public void setRelease_date (String release_date) {
        this.release_date = release_date;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getAdult () {
        return adult;
    }

    public void setAdult (String adult) {
        this.adult = adult;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getPoster_path () {
        return poster_path;
    }

    public void setPoster_path (String poster_path) {
        this.poster_path = poster_path;
    }


    @Override
    public String toString () {
        return "title=" + title +
                ", release_date=" + release_date + ", imdb Url";
    }
}
