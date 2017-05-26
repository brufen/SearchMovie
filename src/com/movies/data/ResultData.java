package com.movies.data;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Maja
 * Combine all data to HashMap
 */
public class ResultData {

    public ResultData () {
    }

    public static HashMap getResultData (List<Movie> moiveList, List<String> resultImdbId) {
        HashMap<Movie, String> map = new HashMap<>();
        int i = 0;
        for (Movie y : moiveList) {
            map.put(y, resultImdbId.get(i));
            i++;
            if (i >= resultImdbId.size()) {
                i = 0;
            }

        }
        return map;
    }

}




