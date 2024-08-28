package com.ericsson.oss.services.cenm.broproxy;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import com.ericsson.oss.services.cenm.broproxy.api.BackupProperties;
import com.ericsson.oss.services.cenm.broproxy.api.Createbackup;
import com.ericsson.oss.services.cenm.broproxy.api.HousekeepingPayload;
import com.ericsson.oss.services.cenm.broproxy.api.IntervalPayload;
import com.ericsson.oss.services.cenm.broproxy.api.SchedulingPayload;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestPostRequest;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestPutRequest;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequest;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequest.HttpRestRequestBuilder;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequest.HttpRestRequestBuilder.RequestMethod;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestRequestSender;
import com.ericsson.oss.services.cenm.broproxy.rest.HttpRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ericsson.oss.itpf.sdk.security.accesscontrol.annotation.Authorize;
import com.ericsson.oss.itpf.sdk.security.accesscontrol.SecurityViolationException;

public class BroProxyService {

        private static final Logger LOGGER = LoggerFactory.getLogger(BroProxyService.class);

        @Inject
        private HttpRestRequestSender httpRestRequestSender;

        @Authorize(resource="broproxy", action="read")
        public Response processGetAllBackupManagers() throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }
        @Authorize(resource="broproxy", action="read")
        public Response processGetBackupManagerByID(String scope) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope);
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="read")
        public Response processScheduleInformation(String scope) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();
        }

        @Authorize(resource="broproxy", action="execute")
        public Response processUpdateSchedulerConfiguration(String scope, final SchedulingPayload schedulingPayload) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler/configuration");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.PUT);

                        HttpRestPutRequest httpRestPutRequest = new HttpRestPutRequest(httpRestRequestBuilder,
                                         new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(schedulingPayload));

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPutRequest.getUrl(),
                                        httpRestPutRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPutRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                         httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                         .build();

         }

         @Authorize(resource="broproxy", action="execute")
         public Response processCreateTimeInterval(String scope, final IntervalPayload intervalPayload) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/" + scope + "/scheduler/periodic-events");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.POST);


                        HttpRestPostRequest httpRestPostRequest = new HttpRestPostRequest(httpRestRequestBuilder,
                                         new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(intervalPayload));

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPostRequest.getUrl(),
                                         httpRestPostRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPostRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                         httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                         .build();

        }




        @Authorize(resource="broproxy", action="read")
        public Response processBroHealth() throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/health");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }
        @Authorize(resource="broproxy", action="read")
        public Response processGetBackupsListbyID(String scope) throws SecurityViolationException, Exception {

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/backup");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="read")
        public Response processGetAllactions(String scope) throws SecurityViolationException, Exception {

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/action");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="read")
        public Response processGetBackupInformation(String scope, String backup_name) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/backup/" + backup_name);
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }
        @Authorize(resource="broproxy", action="read")
        public Response processHousekeepigInformation(String scope) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/" + scope + "/housekeeping");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="execute")
        public Response processUpdateHousekeepigInformation(String scope, final HousekeepingPayload housekeepingpayload) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/" + scope + "/housekeeping");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.POST);

                        JSONObject housekeepingDetails = new JSONObject();

                        housekeepingDetails.put("auto-delete", housekeepingpayload.getAutoDlete());
                        housekeepingDetails.put("max-stored-manual-backups", housekeepingpayload.getMaxStroredBackups());
                        HttpRestPostRequest httpRestPostRequest = new HttpRestPostRequest(httpRestRequestBuilder,housekeepingDetails.toString());
                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPostRequest.getUrl(),
                        httpRestPostRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPostRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="create")
        public Response processCreateBackup(String scope, final Createbackup create_backup) throws SecurityViolationException, Exception{

            HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
            httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/action");
            httpRestRequestBuilder.setRequestMethod(RequestMethod.POST);


            HttpRestPostRequest httpRestPostRequest = new HttpRestPostRequest(httpRestRequestBuilder,
                            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(create_backup));

            LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPostRequest.getUrl(),
                    httpRestPostRequest.getRequestMethod());

            HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPostRequest);

            LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                    httpRestResponse.getResponseData());

            return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                    .build();

        }

        @Authorize(resource="broproxy", action="delete")
        public Response processDeleteBackup(String scope, final Createbackup create_backup) throws SecurityViolationException, Exception{

            HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
            httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/action");
            httpRestRequestBuilder.setRequestMethod(RequestMethod.POST);


            HttpRestPostRequest httpRestPostRequest = new HttpRestPostRequest(httpRestRequestBuilder,
                            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(create_backup));

            LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPostRequest.getUrl(),
                    httpRestPostRequest.getRequestMethod());

            HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPostRequest);

            LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                    httpRestResponse.getResponseData());

            return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                    .build();

        }

        public String retrieve_action(final Createbackup create_backup){
               return create_backup.getAction();
        }

        @Authorize(resource="broproxy", action="execute")
        public Response processImportExportBackup(String scope, final Createbackup create_backup) throws SecurityViolationException, Exception{

            HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
            httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/" + scope + "/action");
            httpRestRequestBuilder.setRequestMethod(RequestMethod.POST);


            HttpRestPostRequest httpRestPostRequest = new HttpRestPostRequest(httpRestRequestBuilder,
                            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(create_backup));

            LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPostRequest.getUrl(),
                    httpRestPostRequest.getRequestMethod());

            HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPostRequest);

            LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                    httpRestResponse.getResponseData());

            return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                    .build();

        }


        @Authorize(resource="broproxy", action="read")
        public Response processActionStatus(String scope, String actionId) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v1/backup-manager/"+ scope +"/action/" + actionId);
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();
        }

        @Authorize(resource="broproxy", action="read")
        public Response processTimeIntervalsInformation(String scope) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler/periodic-events");
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);

                        HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                                        httpRestRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                        httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                        .build();

        }

        @Authorize(resource="broproxy", action="execute")
        public Response processUpdatePeriodicSchedulingEvent(String scope, int actionId, final IntervalPayload intervalPayload) throws SecurityViolationException, Exception{

                        HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
                        httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler/periodic-events/" + actionId);
                        httpRestRequestBuilder.setRequestMethod(RequestMethod.PUT);

                        HttpRestPutRequest httpRestPutRequest = new HttpRestPutRequest(httpRestRequestBuilder,
                                         new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(intervalPayload));

                        LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestPutRequest.getUrl(),
                                        httpRestPutRequest.getRequestMethod());

                        HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest((HttpRestRequest)httpRestPutRequest);

                        LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                                         httpRestResponse.getResponseData());

                        return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                                         .build();



         }


        @Authorize(resource="broproxy", action="delete")
        public Response processDeleteSchedulingEvent (String scope, String action_Id) throws SecurityViolationException, Exception{

            HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
            httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler/periodic-events/"+ action_Id);
            httpRestRequestBuilder.setRequestMethod(RequestMethod.DELETE);


            HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

            LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                     httpRestRequest.getRequestMethod());

            HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

            LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                    httpRestResponse.getResponseData());

            return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                    .build();

        }

        @Authorize(resource="broproxy", action="read")
        public Response processGetSchedulingEvent(String scope,String action_Id) throws SecurityViolationException, Exception{

            HttpRestRequestBuilder httpRestRequestBuilder = new HttpRestRequestBuilder();
            httpRestRequestBuilder.setUrl("http://eric-ctrl-bro:7001/v3/backup-managers/"+ scope +"/scheduler/periodic-events/"+ action_Id);
            httpRestRequestBuilder.setRequestMethod(RequestMethod.GET);


            HttpRestRequest httpRestRequest = new HttpRestRequest(httpRestRequestBuilder);

            LOGGER.info("RequestURL: {} and requestMethod : {} ", httpRestRequest.getUrl(),
                     httpRestRequest.getRequestMethod());

            HttpRestResponse httpRestResponse = httpRestRequestSender.sendHttpRequest(httpRestRequest);

            LOGGER.info("Response headers:{} and Response: {}", httpRestResponse.getHeadersData(),
                    httpRestResponse.getResponseData());

            return Response.status(httpRestResponse.getResponseCode()).entity(httpRestResponse.getResponseData())
                    .build();

        }



}

