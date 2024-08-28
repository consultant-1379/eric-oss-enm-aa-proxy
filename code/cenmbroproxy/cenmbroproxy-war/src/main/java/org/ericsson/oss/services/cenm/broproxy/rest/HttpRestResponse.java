package com.ericsson.oss.services.cenm.broproxy.rest;

import java.util.List;
import java.util.Map;

public class HttpRestResponse {


    private int responseCode;

    private String responseData;

    private Map<String, List<String>> headersData;

    public HttpRestResponse(final int responseCode, final String responseData, final Map<String, List<String>> headersData) {
        super();
        this.responseCode = responseCode;
        this.responseData = responseData;
        this.headersData = headersData;
    }

    public final int getResponseCode() {
        return responseCode;
    }

    public final void setResponseCode(final int responseCode) {
        this.responseCode = responseCode;
    }

    public final String getResponseData() {
        return responseData;
    }

    public final void setResponseData(final String responseData) {
        this.responseData = responseData;
    }

    /**
     * @return the headersData
     */
    public Map<String, List<String>> getHeadersData() {
        return headersData;
    }

    /**
     * @param headersData
     *            the headersData to set
     */
    public void setHeadersData(Map<String, List<String>> headersData) {
        this.headersData = headersData;
    }

    @Override
    public String toString() {
        return "HttpRestResponse [responseCode=" + responseCode + ", responseData=" + responseData + ", headersData=" + headersData + "]";
    }


}
