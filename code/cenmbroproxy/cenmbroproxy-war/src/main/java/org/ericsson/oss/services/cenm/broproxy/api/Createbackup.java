package com.ericsson.oss.services.cenm.broproxy.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Createbackup implements Serializable {

    private static final long serialVersionUID = -8338136202307721548L;

    @JsonProperty("action")
    private String action;

    @JsonProperty("payload")
    private BackupPayload payload;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BackupPayload getPayload() {
        return payload;
    }

    public void setPayload(BackupPayload payload) {
        this.payload = payload;
    }

}
