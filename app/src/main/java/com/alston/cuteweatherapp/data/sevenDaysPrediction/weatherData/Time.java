
package com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("elementValue")
    @Expose
    private List<ElementValue> elementValue = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Time() {
    }

    /**
     * 
     * @param startTime
     * @param elementValue
     * @param endTime
     */
    public Time(String startTime, String endTime, List<ElementValue> elementValue) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.elementValue = elementValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Time withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Time withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<ElementValue> getElementValue() {
        return elementValue;
    }

    public void setElementValue(List<ElementValue> elementValue) {
        this.elementValue = elementValue;
    }

    public Time withElementValue(List<ElementValue> elementValue) {
        this.elementValue = elementValue;
        return this;
    }

}
