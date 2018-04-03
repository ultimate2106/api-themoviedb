/*
 *      Copyright (c) 2004-2016 Stuart Boston
 *
 *      This file is part of TheMovieDB API.
 *
 *      TheMovieDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheMovieDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheMovieDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.omertron.themoviedbapi.enumeration;

import com.omertron.themoviedbapi.interfaces.AppendToResponseMethod;
import org.apache.commons.lang3.StringUtils;

/**
 * List of all "Append To Response" movie methods
 *
 * @author Stuart
 */
public enum MovieMethod implements AppendToResponseMethod {

    ALTERNATIVE_TITLES,
    CHANGES,
    CREDITS,
    IMAGES,
    KEYWORDS,
    LISTS,
    RECOMMENDATIONS,
    RELEASES,
    REVIEWS,
    SIMILAR,
    TRANSLATIONS,
    VIDEOS;

    /**
     * Get the string to use in the URL
     *
     * @return the string representation of the enum
     */
    @Override
    public String getPropertyString() {
        return this.name().toLowerCase();
    }

    /**
     * Convert a string into an Enum type
     *
     * @param method String to convert to enum
     * @return enum version of param
     * @throws IllegalArgumentException If type is not recognised
     *
     */
    public static MovieMethod fromString(String method) {
        if (StringUtils.isNotBlank(method)) {
            try {
                return MovieMethod.valueOf(method.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Method " + method + " does not exist.", ex);
            }
        }
        throw new IllegalArgumentException("Method must not be null");
    }
}
