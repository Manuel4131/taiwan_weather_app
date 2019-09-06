
package com.test.weatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherElement {

    @SerializedName("elementName")
    @Expose
    private String elementName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time")
    @Expose
    private List<Time> time = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherElement() {
    }

    /**
     * 
     * @param time
     * @param elementName
     * @param description
     */
    public WeatherElement(String elementName, String description, List<Time> time) {
        super();
        this.elementName = elementName;
        this.description = description;
        this.time = time;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public WeatherElement withElementName(String elementName) {
        this.elementName = elementName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeatherElement withDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

    public WeatherElement withTime(List<Time> time) {
        this.time = time;
        return this;
    }

}
