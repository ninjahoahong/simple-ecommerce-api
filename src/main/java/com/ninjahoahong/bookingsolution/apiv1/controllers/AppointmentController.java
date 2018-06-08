package com.ninjahoahong.bookingsolution.apiv1.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("appointments")
public class AppointmentController {

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
