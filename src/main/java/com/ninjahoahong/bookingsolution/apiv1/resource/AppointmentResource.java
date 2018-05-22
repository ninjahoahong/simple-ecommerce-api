package com.ninjahoahong.bookingsolution.apiv1.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("appointments")
public class AppointmentResource {

    @GET
    @Path("{id}")
    public String getAppointmentById(@PathParam("id") String id) {
        return id;
    }

    @POST
    public String createAppointment(@PathParam("id") String id) {
        return "Post " + id;
    }

}
