package com.ericsson.oss.services.cenm.broproxy;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.ericsson.oss.services.cenm.broproxy.api.Createbackup;
import com.ericsson.oss.services.cenm.broproxy.api.BackupProperties;
import com.ericsson.oss.services.cenm.broproxy.api.HousekeepingPayload;
import com.ericsson.oss.services.cenm.broproxy.api.IntervalPayload;
import com.ericsson.oss.services.cenm.broproxy.api.SchedulingPayload;
import com.ericsson.oss.itpf.sdk.security.accesscontrol.SecurityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;

import com.ericsson.oss.services.cenm.broproxy.BroProxyService;

@Path("/backup")
public class BroProxyResource {

        private static final Logger LOGGER = LoggerFactory.getLogger(BroProxyResource.class);

        @Inject
        BroProxyService broProxyService;

        @Path("/v1/backup-manager")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllBackupManagers() {
               try {
                    LOGGER.info("Received request for /v1/backup-manager");
                    return broProxyService.processGetAllBackupManagers();
                   }
               catch (SecurityViolationException exception) {
                   LOGGER.error("Unauthorized user : {}", exception);
                   return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                   e.printStackTrace();
                   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v1/backup-manager/{id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBackupManagerById(@PathParam("id") String scope) {
               try {
                    LOGGER.info("Received request for /v1/backup-manager/{id}");
                    return broProxyService.processGetBackupManagerByID(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v3/backup-managers/{id}/scheduler")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSchedulingInformation(@PathParam("id") String scope){
               try {
                      LOGGER.info("Received request for /v3/backup-managers");
                      return broProxyService.processScheduleInformation(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                    }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                   }
        }

        @Path("/v3/backup-managers/{id}/scheduler/configuration")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSchedulingConfigurationInformation(@PathParam("id") String scope){
               try {
                      LOGGER.info("Received request for /v3/backup-managers");
                      return broProxyService.processScheduleInformation(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                    }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                   }
        }

        @Path("/v3/backup-managers/{id}/scheduler/configuration")
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)

        public Response UpdateSchedulerConfiguration(@PathParam("id") String scope , final SchedulingPayload schedulingPayload){
               try {
                      LOGGER.info("Received request for /v3/backup-managers");
                      return broProxyService.processUpdateSchedulerConfiguration(scope,schedulingPayload);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                    }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                   }
        }

        @Path("/v3/backup-managers/{id}/scheduler/periodic-events")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response CreateTimeInterval(@PathParam("id") String scope, final IntervalPayload intervalPayload){
               try {
                      LOGGER.info("Received request for /v3/backup-managers");
                      return broProxyService.processCreateTimeInterval(scope, intervalPayload);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                    }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                   }
        }

        @Path("/v1/health")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBroHealth(){
               try {
                      LOGGER.info("Received request for /v1/health");
                      return broProxyService.processBroHealth();
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v1/backup-manager/{id}/backup")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBackupsList(@PathParam("id") String scope) {
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/backup");
                     return broProxyService.processGetBackupsListbyID(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v1/backup-manager/{id}/action")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllactions(@PathParam("id") String scope) {
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/action");
                     return broProxyService.processGetAllactions(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v1/backup-manager/{id}/backup/{backupName}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBackupInformation(@PathParam("id") String scope , @PathParam("backupName") String backup_name ) {
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/backup/{backupName}");
                     return broProxyService.processGetBackupInformation(scope,backup_name);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v3/backup-managers/{id}/housekeeping")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getHousekeepingInformation(@PathParam("id") String scope){
               try {
                     LOGGER.info("Received request for /v3/backup-managers/{id}/housekeeping");
                     return broProxyService.processHousekeepigInformation(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v3/backup-managers/{id}/housekeeping")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response getUpdateHousekeepingInformation(@PathParam("id") String scope, final HousekeepingPayload housekeepingPayload){
               try {
                     LOGGER.info("Received request for /v3/backup-managers/{id}/housekeeping");
                     return broProxyService.processUpdateHousekeepigInformation(scope,housekeepingPayload);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("v1/backup-manager/{Id}/action")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response createbackup(@PathParam("Id") String scope, final Createbackup create_backup){
            String Action = broProxyService.retrieve_action(create_backup);
                if ( Action == "CREATE_BACKUP" ){
                     try {
                           LOGGER.info("Received request for update /v1/backup-manager/{id}/action");
                           return broProxyService.processCreateBackup(scope,create_backup);
                         }
                     catch (SecurityViolationException exception) {
                           LOGGER.error("Unauthorized user : {}", exception);
                           return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                         }
                     catch (Exception e) {
                           e.printStackTrace();
                           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                          }
                   }
                else if ( Action == "DELETE_BACKUP") {
                      try {
                            LOGGER.info("Received request for update /v1/backup-manager/{id}/action");
                            return broProxyService.processDeleteBackup(scope,create_backup);
                          }
                      catch (SecurityViolationException exception) {
                            LOGGER.error("Unauthorized user : {}", exception);
                            return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                          }
                      catch (Exception e) {
                            e.printStackTrace();
                            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                         }
                   }
                else {
                      try {
                            LOGGER.info("Received request for update /v1/backup-manager/{id}/action");
                            return broProxyService.processImportExportBackup(scope,create_backup);
                          }
                      catch (SecurityViolationException exception) {
                            LOGGER.error("Unauthorized user : {}", exception);
                            return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                          }
                      catch (Exception e) {
                            e.printStackTrace();
                            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                          }
                    }
         }

        @Path("/v1/backup-manager/{id}/action/{action_Id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBroHealth(@PathParam("id") String scope, @PathParam("action_Id") String actionId){
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/action/{action_Id}");
                     return broProxyService.processActionStatus(scope, actionId);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v3/backup-managers/{id}/scheduler/periodic-events")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getTimeIntervalsInformation(@PathParam("id") String scope){
               try {
                      LOGGER.info("Received request for /v3/backup-managers/{id}/scheduler/periodic-events");
                      return broProxyService.processTimeIntervalsInformation(scope);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                    }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                   }
        }

        @Path("/v3/backup-managers/{id}/scheduler/periodic-events/{action_Id}")
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response UpdatePeriodicSchedulingEvent(@PathParam("id") String scope , @PathParam("action_Id") int actionId , final IntervalPayload intervalPayload){
               try {
                     LOGGER.info("Received request for /v3/backup-managers/{id}/scheduler/periodic-events/{action_Id}");
                     return broProxyService.processUpdatePeriodicSchedulingEvent(scope, actionId, intervalPayload);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }

        @Path("/v3/backup-managers/{id}/scheduler/periodic-events/{action_Id}")
        @DELETE
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response DeleteSchedulingEvent(@PathParam("id") String scope, @PathParam("action_Id") String actionId){
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/scheduler/periodic-events/{action_Id}");
                     return broProxyService.processDeleteSchedulingEvent(scope, actionId);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }
      @Path("/v3/backup-managers/{id}/scheduler/periodic-events/{action_Id}")
      @GET
      @Consumes(MediaType.APPLICATION_JSON)
      @Produces(MediaType.APPLICATION_JSON)
      public Response GetSchedulingEvent(@PathParam("id") String scope , @PathParam("action_Id") String actionId){
               try {
                     LOGGER.info("Received request for /v1/backup-manager/{id}/scheduler/periodic-events/{action_Id}");
                     return broProxyService.processGetSchedulingEvent(scope, actionId);
                   }
               catch (SecurityViolationException exception) {
                    LOGGER.error("Unauthorized user : {}", exception);
                    return Response.status(Status.UNAUTHORIZED).entity("Insufficient access rights to perform the operation").build();
                   }
               catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                  }
        }
}

