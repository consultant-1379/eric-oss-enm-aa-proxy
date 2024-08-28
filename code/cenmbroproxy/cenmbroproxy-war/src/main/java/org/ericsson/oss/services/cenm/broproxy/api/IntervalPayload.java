package com.ericsson.oss.services.cenm.broproxy.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class IntervalPayload implements Serializable {

    private static final long serialVersionUID = -8338136202307721548L;

    @JsonProperty("hours")
    private int hours;

    @JsonProperty("minutes")
    private int minutes;

    @JsonProperty("days")
    private int days;

    @JsonProperty("weeks")
    private int weeks;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("stopTime")
    private String stopTime;


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours= hours;
    }

     public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes= minutes;
    }

     public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days= days;
    }

     public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks= weeks;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime= startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime= stopTime;
    }

}

