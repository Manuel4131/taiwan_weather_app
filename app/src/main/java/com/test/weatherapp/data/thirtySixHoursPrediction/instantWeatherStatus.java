package com.test.weatherapp.data.thirtySixHoursPrediction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class instantWeatherStatus {

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
    public instantWeatherStatus() {
    }

    /**
     *
     * @param result
     * @param records
     * @param success
     */
    public instantWeatherStatus(String success, Result result, Records records) {
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

    public instantWeatherStatus withSuccess(String success) {
        this.success = success;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public instantWeatherStatus withResult(Result result) {
        this.result = result;
        return this;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public instantWeatherStatus withRecords(Records records) {
        this.records = records;
        return this;
    }

}

