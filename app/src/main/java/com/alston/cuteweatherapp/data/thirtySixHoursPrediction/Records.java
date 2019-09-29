
package com.alston.cuteweatherapp.data.thirtySixHoursPrediction;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {

    @SerializedName("datasetDescription")
    @Expose
    private String datasetDescription;
    @SerializedName("location")
    @Expose
    private List<Location> location = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Records() {
    }

    /**
     * 
     * @param location
     * @param datasetDescription
     */
    public Records(String datasetDescription, List<Location> location) {
        super();
        this.datasetDescription = datasetDescription;
        this.location = location;
    }

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public Records withDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
        return this;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public Records withLocation(List<Location> location) {
        this.location = location;
        return this;
    }

}
