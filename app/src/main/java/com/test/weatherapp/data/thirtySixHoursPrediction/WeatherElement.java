
package com.test.weatherapp.data.thirtySixHoursPrediction;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.test.weatherapp.utils.CheckNull.checkNull;

public class WeatherElement {

    @SerializedName("elementName")
    @Expose
    private String elementName;
    @SerializedName("time")
    @Expose
    private List<WBIntervals> time = null;

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
     */
    public WeatherElement(String elementName, List<WBIntervals> time) {
        super();
        this.elementName = elementName;
        this.time = time;
    }

    public String getElementName() {
        return checkNull(elementName);
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public WeatherElement withElementName(String elementName) {
        this.elementName = elementName;
        return this;
    }

    public List<WBIntervals> getTime() {
        return time;
    }

    public void setTime(List<WBIntervals> time) {
        this.time = time;
    }

    public WeatherElement withTime(List<WBIntervals> WBIntervals) {
        this.time = WBIntervals;
        return this;
    }

}
