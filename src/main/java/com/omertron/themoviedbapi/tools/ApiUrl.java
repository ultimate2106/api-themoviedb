/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.omertron.themoviedbapi.tools;

import com.omertron.themoviedbapi.TheMovieDbApi;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * The API URL that is used to construct the API call
 *
 * @author Stuart
 */
public class ApiUrl {

    /*
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ApiUrl.class);
    /*
     * TheMovieDbApi API Base URL
     */
    private static final String TMDB_API_BASE = "http://api.themoviedb.org/3/";
//    private static final String TMDB_API_BASE = "http://private-3aa3-themoviedb.apiary.io/3/";
    /*
     * Parameter configuration
     */
    private static final String DELIMITER_FIRST = "?";
    private static final String DELIMITER_SUBSEQUENT = "&";
    private static final String DEFAULT_STRING = "";
    /*
     * Properties
     */
    private TheMovieDbApi tmdb;
    private String method;
    private String submethod;
    private Map<String, String> arguments = new HashMap<String, String>();
    /*
     * API Parameters
     */
    public static final String PARAM_ADULT = "include_adult=";
    public static final String PARAM_API_KEY = "api_key=";
    public static final String PARAM_COUNTRY = "country=";
    public static final String PARAM_FAVORITE = "favorite=";
    public static final String PARAM_ID = "id=";
    public static final String PARAM_LANGUAGE = "language=";
//    public static final String PARAM_MOVIE_ID = "movie_id=";
    public static final String PARAM_MOVIE_WATCHLIST = "movie_watchlist=";
    public static final String PARAM_PAGE = "page=";
    public static final String PARAM_QUERY = "query=";
    public static final String PARAM_SESSION = "session_id=";
    public static final String PARAM_TOKEN = "request_token=";
    public static final String PARAM_VALUE = "value=";
    public static final String PARAM_YEAR = "year=";

    //<editor-fold defaultstate="collapsed" desc="Constructor Methods">
    /**
     * Constructor for the simple API URL method without a sub-method
     *
     * @param method
     */
    public ApiUrl(TheMovieDbApi tmdb, String method) {
        this.tmdb = tmdb;
        this.method = method;
        this.submethod = DEFAULT_STRING;
    }

    /**
     * Constructor for the API URL with a sub-method
     *
     * @param method
     * @param submethod
     */
    public ApiUrl(TheMovieDbApi tmdb, String method, String submethod) {
        this.tmdb = tmdb;
        this.method = method;
        this.submethod = submethod;
    }
    //</editor-fold>

    /**
     * Build the URL from the pre-created arguments.
     *
     * @return
     */
    public URL buildUrl() {
        StringBuilder urlString = new StringBuilder(TMDB_API_BASE);

        // Get the start of the URL
        urlString.append(method);

        // We have either a queury, or a direct request
        if (arguments.containsKey(PARAM_QUERY)) {
            // Append the suffix of the API URL
            urlString.append(submethod);

            // Append the key information
            urlString.append(DELIMITER_FIRST).append(PARAM_API_KEY);
            urlString.append(tmdb.getApiKey());

            // Append the search term
            urlString.append(DELIMITER_SUBSEQUENT);
            urlString.append(PARAM_QUERY);

            String query = arguments.get(PARAM_QUERY);

            try {
                urlString.append(URLEncoder.encode(query, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                LOGGER.trace("Unable to encode query: '" + query + "' trying raw.");
                // If we can't encode it, try it raw
                urlString.append(query);
            }

            arguments.remove(PARAM_QUERY);
        } else {
            // Append the ID if provided
            if (arguments.containsKey(PARAM_ID)) {
                urlString.append(arguments.get(PARAM_ID));
                arguments.remove(PARAM_ID);
            }

            // Append the suffix of the API URL
            urlString.append(submethod);

            // Append the key information
            urlString.append(DELIMITER_FIRST).append(PARAM_API_KEY);
            urlString.append(tmdb.getApiKey());
        }

        for (Map.Entry<String, String> argEntry : arguments.entrySet()) {
            urlString.append(DELIMITER_SUBSEQUENT).append(argEntry.getKey());
            urlString.append(argEntry.getValue());
        }

        try {
            LOGGER.trace("URL: " + urlString.toString());
            return new URL(urlString.toString());
        } catch (MalformedURLException ex) {
            LOGGER.warn("Failed to create URL " + urlString.toString() + " - " + ex.toString());
            return null;
        } finally {
            arguments.clear();
        }
    }

    /**
     * Add arguments individually
     *
     * @param key
     * @param value
     */
    public void addArgument(String key, String value) {
        arguments.put(key, value);
    }

    /**
     * Add arguments individually
     *
     * @param key
     * @param value
     */
    public void addArgument(String key, int value) {
        arguments.put(key, Integer.toString(value));
    }

    /**
     * Add arguments individually
     *
     * @param key
     * @param value
     */
    public void addArgument(String key, boolean value) {
        arguments.put(key, Boolean.toString(value));
    }

    /**
     * Clear the arguments
     */
    public void clearArguments() {
        arguments.clear();
    }

    /**
     * Set the arguments directly
     *
     * @param args
     */
    public void setArguments(Map<String, String> args) {
        arguments.putAll(args);
    }
}