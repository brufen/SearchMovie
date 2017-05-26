package com.movies.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maja
 */
public class Actor {
    private String id;
    private String name;
    List<String> movies;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<String> getMovies () {
        return movies;
    }

    public void setMovies (List<String> movies) {
        this.movies = movies;
    }


    public Actor (String id, String name) {
        this.id = id;
        this.name = name;
        movies = new ArrayList<String>();
    }

    public void addMovies (String tempMovie) {
        System.out.println("tempMovies");
        movies.add(tempMovie);
    }

    public void printMovies () {
        movies.forEach(System.out::println);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(getId(), actor.getId()) &&
                Objects.equals(getName(), actor.getName()) &&
                Objects.equals(getMovies(), actor.getMovies());
    }

    @Override
    public int hashCode () {
        return Objects.hash(getId(), getName(), getMovies());
    }

    @Override
    public String toString () {
        return "Actor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
