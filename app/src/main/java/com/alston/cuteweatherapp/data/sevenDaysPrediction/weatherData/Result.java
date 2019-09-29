
package com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("resource_id")
    @Expose
    private String resourceId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param resourceId
     * @param fields
     */
    public Result(String resourceId, List<Field> fields) {
        super();
        this.resourceId = resourceId;
        this.fields = fields;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Result withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Result withFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

}
