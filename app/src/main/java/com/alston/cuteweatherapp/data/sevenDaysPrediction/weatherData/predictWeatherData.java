
package com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class predictWeatherData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("records")
    @Expose
    private Records records;

    /**
     * No args constructor for use in serialization
     * 
     */
    public predictWeatherData() {
    }

    /**
     * 
     * @param result
     * @param records
     * @param success
     */
    public predictWeatherData(String success, Result result, Records records) {
        super();
        this.success = success;
        this.result = result;
        this.records = records;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public predictWeatherData withSuccess(String success) {
        this.success = success;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public predictWeatherData withResult(Result result) {
        this.result = result;
        return this;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public predictWeatherData withRecords(Records records) {
        this.records = records;
        return this;
    }

}
