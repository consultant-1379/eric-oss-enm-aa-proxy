package com.ericsson.oss.services.cenm.broproxy.rest;


import java.util.Map;

import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequest.HttpRestRequestBuilder.RequestMethod;


/**
 * Holds the HttpRestRequest related data.
 *
 */
public class HttpRestRequest {

    private final String url;

    private final RequestMethod requestMethod;

    private final Map<String, String> headers;

    public HttpRestRequest(HttpRestRequestBuilder builder) {
        this.url = builder.url;
        this.headers = builder.headers;
        this.requestMethod = builder.requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public static class HttpRestRequestBuilder {

        public enum RequestMethod {
            GET,
	       PUT,
	         DELETE,
        	     POST;
        }

        private String url;

        private RequestMethod requestMethod;

        private Map<String, String> headers;

        public HttpRestRequestBuilder setUrl(final String url) {
            this.url = url;
            return this;
        }

        public HttpRestRequestBuilder setHeaders(final Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpRestRequestBuilder setRequestMethod(final RequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public HttpRestRequest build() {
            return new HttpRestRequest(this);
        }

        @Override
        public String toString() {
            return "HttpRestRequest [url=" + url + ", requestMethod=" + requestMethod + ", headers=" + headers + "]";
        }

    }

    @Override
    public String toString() {
        return "HttpRestRequest [url=" + url + ", requestMethod=" + requestMethod + ", headers=" + headers + "]";
    }
}

