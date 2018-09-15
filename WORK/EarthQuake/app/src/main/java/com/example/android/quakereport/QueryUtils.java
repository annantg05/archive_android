package com.example.android.quakereport;
/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */



      private QueryUtils() {
    }
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */


}