
package com.test.weatherapp.data.thirtySixHoursPrediction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.test.weatherapp.utils.CheckNull.checkNull;

public class Parameter {

    @SerializedName("parameterName")
    @Expose
    private String parameterName;
    @SerializedName("parameterValue")
    @Expose
    private String parameterValue;
    @SerializedName("parameterUnit")
    @Expose
    private String parameterUnit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Parameter() {
    }

    /**
     * 
     * @param parameterValue
     * @param parameterUnit
     * @param parameterName
     */
    public Parameter(String parameterName, String parameterValue, String parameterUnit) {
        super();
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.parameterUnit = parameterUnit;
    }

    public String getParameterName() {
        return checkNull(parameterName);
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return checkNull(parameterValue);
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterUnit() {
        return checkNull(parameterUnit);
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

}
