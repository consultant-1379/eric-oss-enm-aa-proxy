package com.ericsson.oss.services.cenm.broproxy.api;
import java.io.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackupProperties {

    private String backupType;

    private String backupDomain;

    
    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public String getBackupDomain() {
        return backupDomain;
    }

    public void setBackupDomain(String backupDomain) {
        this.backupDomain = backupDomain;
    }

}
