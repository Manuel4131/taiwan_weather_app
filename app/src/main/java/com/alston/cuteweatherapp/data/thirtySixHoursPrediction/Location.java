
package com.alston.cuteweatherapp.data.thirtySixHoursPrediction;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("weatherElement")
    @Expose
    private List<WeatherElement> weatherElement = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param weatherElement
     * @param locationName
     */
    public Location(String locationName, List<WeatherElement> weatherElement) {
        super();
        this.locationName = locationName;
        this.weatherElement = weatherElement;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Location withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }

    public Location withWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
        return this;
    }

}
