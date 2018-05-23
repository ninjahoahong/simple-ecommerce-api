package com.ninjahoahong.bookingsolution.apiv1.controllers;

import com.google.api.client.util.Lists;
import com.ninjahoahong.bookingsolution.entities.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Path("products")
public class ProductController {

    @GET
    @Path("{code}")
    public Response getProductByCode(@PathParam("code") String code) {
        Product product = ofy().load().type(Product.class).id(code).now();
        return Response.ok(product).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> products = ofy().load().type(Product.class).list();
        if (products == null) {
            Response.status(203).build();
        }
        GenericEntity<List<Product>> entity =
                new GenericEntity<List<Product>>(Lists.newArrayList(products)) {};
        return Response.ok(entity).build();
    }
}
