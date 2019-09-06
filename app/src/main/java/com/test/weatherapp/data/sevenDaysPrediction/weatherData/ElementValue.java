
package com.test.weatherapp.data.sevenDaysPrediction.weatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementValue {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("measures")
    @Expose
    private String measures;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ElementValue() {
    }

    /**
     * 
     * @param value
     * @param measures
     */
    public ElementValue(String value, String measures) {
        super();
        this.value = value;
        this.measures = measures;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ElementValue withValue(String value) {
        this.value = value;
        return this;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public ElementValue withMeasures(String measures) {
        this.measures = measures;
        return this;
    }

}
