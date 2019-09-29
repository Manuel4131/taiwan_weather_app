
package com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("datasetDescription")
    @Expose
    private String datasetDescription;
    @SerializedName("locationsName")
    @Expose
    private String locationsName;
    @SerializedName("dataid")
    @Expose
    private String dataid;
    @SerializedName("location")
    @Expose
    private List<Location_> location = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param location
     * @param locationsName
     * @param datasetDescription
     * @param dataid
     */
    public Location(String datasetDescription, String locationsName, String dataid, List<Location_> location) {
        super();
        this.datasetDescription = datasetDescription;
        this.locationsName = locationsName;
        this.dataid = dataid;
        this.location = location;
    }

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public Location withDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
        return this;
    }

    public String getLocationsName() {
        return locationsName;
    }

    public void setLocationsName(String locationsName) {
        this.locationsName = locationsName;
    }

    public Location withLocationsName(String locationsName) {
        this.locationsName = locationsName;
        return this;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public Location withDataid(String dataid) {
        this.dataid = dataid;
        return this;
    }

    public List<Location_> getLocation() {
        return location;
    }

    public void setLocation(List<Location_> location) {
        this.location = location;
    }

    public Location withLocation(List<Location_> location) {
        this.location = location;
        return this;
    }

}
