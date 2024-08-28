package com.ericsson.oss.services.cenm.broproxy.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulingPayload implements Serializable {

    private static final long serialVersionUID = -8338136202307721548L;

    @JsonProperty("adminState")
    private String adminState;

    @JsonProperty("scheduledBackupName")
    private String scheduledBackupName;

    @JsonProperty("autoExport")
    private String autoExport;

    @JsonProperty("autoExportUri")
    private String autoExportUri;

    @JsonProperty("autoExportPassword")
    private String autoExportPassword;





    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    public String getScheduledBackupName() {
        return scheduledBackupName;
    }

    public void setScheduledBackupName(String scheduledBackupName) {
        this.scheduledBackupName= scheduledBackupName;
    }

    public String getAutoExport() {
        return autoExport;
    }

    public void setAutoExport(String autoExport) {
        this.autoExport= autoExport;
    }

    public String getAutoExportUri() {
        return autoExportUri;
    }

    public void setAutoExportUri(String autoExportUri) {
        this.autoExportUri= autoExportUri;
    }

    public String getAutoExportPassword() {
        return autoExportPassword;
    }

    public void setAutoExportPassword(String autoExportPassword) {
        this.autoExportPassword= autoExportPassword;
    }


}

