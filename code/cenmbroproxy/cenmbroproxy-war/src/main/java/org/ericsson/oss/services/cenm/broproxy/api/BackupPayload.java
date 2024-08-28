package com.ericsson.oss.services.cenm.broproxy.api;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BackupPayload implements Serializable{
    private static final long serialVersionUID = 5731162748641499637L;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("password")
    private String password;

    @JsonProperty("backupName")
    private String backupName;

    public String getBackupName() {
        return backupName;
    }

    public void setBackupName(String backupName) {
        this.backupName = backupName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
