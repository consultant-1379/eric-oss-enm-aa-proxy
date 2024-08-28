package com.ericsson.oss.services.cenm.broproxy.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HousekeepingPayload implements Serializable {

    private static final long serialVersionUID = -8338136202307721548L;

    @JsonProperty("auto-delete")
    private String auto_delete;

    @JsonProperty("max-stored-manual-backups")
    private int max_stored_manual_backups;


    public String getAutoDlete() {
        return auto_delete;
    }

    public void setAutoDelete(String auto_delete) {
        this.auto_delete= auto_delete;
    }

    public int getMaxStroredBackups() {
        return max_stored_manual_backups;
    }

    public void setMaxStroredBackups(int max_stored_manual_backups) {
        this.max_stored_manual_backups= max_stored_manual_backups;
    }
}

