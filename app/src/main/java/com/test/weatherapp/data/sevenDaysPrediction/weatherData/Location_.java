
package com.test.weatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location_ {

    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("geocode")
    @Expose
    private String geocode;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("weatherElement")
    @Expose
    private List<WeatherElement> weatherElement = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location_() {
    }

    /**
     * 
     * @param geocode
     * @param lon
     * @param weatherElement
     * @param locationName
     * @param lat
     */
    public Location_(String locationName, String geocode, String lat, String lon, List<WeatherElement> weatherElement) {
        super();
        this.locationName = locationName;
        this.geocode = geocode;
        this.lat = lat;
        this.lon = lon;
        this.weatherElement = weatherElement;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Location_ withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public Location_ withGeocode(String geocode) {
        this.geocode = geocode;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Location_ withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Location_ withLon(String lon) {
        this.lon = lon;
        return this;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }

    public Location_ withWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
        return this;
    }

}
