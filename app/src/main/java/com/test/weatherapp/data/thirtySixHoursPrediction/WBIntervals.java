
package com.test.weatherapp.data.thirtySixHoursPrediction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.test.weatherapp.utils.CheckNull.checkNull;

public class WBIntervals {

    @SerializedName("startTime")
    @Expose
    public String startTime;
    @SerializedName("endTime")
    @Expose
    public String endTime;
    @SerializedName("parameter")
    @Expose
    public Parameter parameter;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WBIntervals() {
    }

    /**
     * 
     * @param startTime
     * @param parameter
     * @param endTime
     */
    public WBIntervals(String startTime, String endTime, Parameter parameter) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.parameter = parameter;
    }

    public String getStartTime() {
        return checkNull(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return checkNull(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

}
