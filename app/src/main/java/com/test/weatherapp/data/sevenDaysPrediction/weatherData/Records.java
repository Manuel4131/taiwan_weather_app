
package com.test.weatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {

    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Records() {
    }

    /**
     * 
     * @param locations
     */
    public Records(List<Location> locations) {
        super();
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Records withLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

}
