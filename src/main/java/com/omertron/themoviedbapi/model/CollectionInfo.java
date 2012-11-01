/*
 *      Copyright (c) 2004-2012 Stuart Boston
 *
 *      This software is licensed under a Creative Commons License
 *      See the LICENCE.txt file included in this package
 *
 *      For any reuse or distribution, you must make clear to others the
 *      license terms of this work.
 */
package com.omertron.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Stuart
 */
public class CollectionInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(CollectionInfo.class);
    /*
     * Properties
     */
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("parts")
    private List<Collection> parts = new ArrayList<Collection>();

    //<editor-fold defaultstate="collapsed" desc="Getter methods">
    public String getBackdropPath() {
        return backdropPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Collection> getParts() {
        return parts;
    }

    public String getPosterPath() {
        return posterPath;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParts(List<Collection> parts) {
        this.parts = parts;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    //</editor-fold>

    /**
     * Handle unknown properties and print a message
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown property: '").append(key);
        sb.append("' value: '").append(value).append("'");
        LOGGER.trace(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[CollectionInfo=");
        sb.append("[id=").append(id);
        sb.append("],[name=").append(name);
        sb.append("],[posterPath=").append(posterPath);
        sb.append("],[backdropPath=").append(backdropPath);
        sb.append("],[# of parts=").append(parts.size());
        sb.append("]]");
        return sb.toString();
    }
}