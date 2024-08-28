package com.ericsson.oss.services.cenm.broproxy.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequest.HttpRestRequestBuilder;

public class HttpRestRequestSender {

        private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestRequestSender.class);

        /**
         * Send HTTP request based on the provided client input.
         *
         * @param httpRestRequest
         *            {@link RestHttpClientInput}
         * @return HTTP response data {@link HttpResponseData}, null will be
         *         returned if the client input is invalid.
         * @throws IOException
         */
        public HttpRestResponse sendHttpRequest(final HttpRestRequest httpRestRequest) throws IOException {
                LOGGER.info("Triggering HTTP request using :{}", httpRestRequest);
                if (HttpRestRequestBuilder.RequestMethod.GET.equals(httpRestRequest.getRequestMethod())) {
                        return getHttpResponse(httpRestRequest);
                }else if (HttpRestRequestBuilder.RequestMethod.POST.equals(httpRestRequest.getRequestMethod())) {
                        return getHttpPostResponse((HttpRestPostRequest) httpRestRequest);
                }else if (HttpRestRequestBuilder.RequestMethod.PUT.equals(httpRestRequest.getRequestMethod())) {
                        return getHttpPutResponse((HttpRestPutRequest) httpRestRequest);
                }else if (HttpRestRequestBuilder.RequestMethod.DELETE.equals(httpRestRequest.getRequestMethod())) {
                        return getHttpResponse(httpRestRequest);
                }else {
                        LOGGER.error("Unable to send request as the request method '{}' is invalid..",
                                        httpRestRequest.getRequestMethod());
                        return null;
                }
        }


        private HttpRestResponse getHttpPutResponse(HttpRestPutRequest httpRestPutRequest) throws IOException {
                HttpURLConnection httpURLConnection = null;
                try {

                        httpURLConnection = (HttpURLConnection) new URL(httpRestPutRequest.getUrl()).openConnection();
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestMethod(httpRestPutRequest.getRequestMethod().toString());
                        httpURLConnection.setRequestProperty("Content-type", "application/json");
                        if (httpRestPutRequest.getHeaders() != null) {
                                buildHeaders(httpRestPutRequest.getHeaders(), httpURLConnection);
                        }
                        LOGGER.info("RequestProperties: {} and requestMethod : {} ", httpURLConnection.getRequestProperties(),
                                        httpURLConnection.getRequestMethod());

                        final OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                        writer.write(httpRestPutRequest.getJsonInput());
                        writer.close();
                        final int responseCode = httpURLConnection.getResponseCode();

                        LOGGER.info("Response headers:{}", httpURLConnection.getHeaderFields());
                        final String responseData = readResponseData(httpURLConnection);
                        LOGGER.info("URL: {}, responsecode: {}", httpRestPutRequest.getUrl(), responseCode);
                        return new HttpRestResponse(responseCode, responseData, httpURLConnection.getHeaderFields());
                } finally {
                        if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                        }
                }
        }

        private HttpRestResponse getHttpPostResponse(HttpRestPostRequest httpRestPostRequest) throws IOException {
                HttpURLConnection httpURLConnection = null;
                try {

                        httpURLConnection = (HttpURLConnection) new URL(httpRestPostRequest.getUrl()).openConnection();
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestMethod(httpRestPostRequest.getRequestMethod().toString());
                        httpURLConnection.setRequestProperty("Content-type", "application/json");
                        if (httpRestPostRequest.getHeaders() != null) {
                                buildHeaders(httpRestPostRequest.getHeaders(), httpURLConnection);
                        }
                        LOGGER.info("RequestProperties: {} and requestMethod : {} ", httpURLConnection.getRequestProperties(),
                                        httpURLConnection.getRequestMethod());

                        final OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                        writer.write(httpRestPostRequest.getJsonInput());
                        writer.close();
                        final int responseCode = httpURLConnection.getResponseCode();

                        LOGGER.info("Response headers:{}", httpURLConnection.getHeaderFields());
                        final String responseData = readResponseData(httpURLConnection);
                        LOGGER.info("URL: {}, responsecode: {}", httpRestPostRequest.getUrl(), responseCode);
                        return new HttpRestResponse(responseCode, responseData, httpURLConnection.getHeaderFields());
                } finally {
                        if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                        }
                }
        }

        private HttpRestResponse getHttpResponse(final HttpRestRequest client) throws IOException {
                HttpURLConnection httpURLConnection = null;
                try {
                        httpURLConnection = (HttpURLConnection) new URL(client.getUrl()).openConnection();
                        httpURLConnection.setRequestMethod(client.getRequestMethod().toString());
                        if (client.getHeaders() != null) {
                                buildHeaders(client.getHeaders(), httpURLConnection);
                        }
                        LOGGER.debug("RequestProperties: {} and requestMethod : {} ", httpURLConnection.getRequestProperties(),
                                        httpURLConnection.getRequestMethod());
                        final int responseCode = httpURLConnection.getResponseCode();
                        LOGGER.debug("Response headers:{}", httpURLConnection.getHeaderFields());
                        final String responseData = readResponseData(httpURLConnection);
                        LOGGER.info("URL: {}, responsecode: {}", client.getUrl(), responseCode);
                        return new HttpRestResponse(responseCode, responseData, httpURLConnection.getHeaderFields());
                } finally {
                        if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                        }
                }
        }

        /**
         * Get response Data from the httpURLConnection.
         *
         * @param httpURLConnection
         *            {@link HttpURLConnection}
         * @return Response Data as String
         * @throws IOException
         */
        private String readResponseData(final HttpURLConnection httpURLConnection) throws IOException {
                InputStream inputStream = null;
                String responseLine;
                if (httpURLConnection.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                        inputStream = httpURLConnection.getErrorStream();
                } else {
                        inputStream = httpURLConnection.getInputStream();
                }
                StringBuilder responseData = null;
                try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                        responseData = new StringBuilder();
                        while ((responseLine = br.readLine()) != null) {
                                responseData.append(responseLine);
                        }
                        return responseData.toString();
                } finally {
                        if (inputStream != null) {
                                inputStream.close();
                        }
                }
        }

        private HttpURLConnection buildHeaders(final Map<String, String> httpHeaderParameters,
                        final HttpURLConnection httpURLConnection) {
                for (Entry<String, String> httpHeader : httpHeaderParameters.entrySet()) {
                        httpURLConnection.setRequestProperty(httpHeader.getKey(), httpHeader.getValue());
                }
                return httpURLConnection;
        }

}

