package com.ninjahoahong.bookingsolution.apiv1.controllers;

import com.google.api.client.util.Lists;
import com.ninjahoahong.bookingsolution.entities.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Path("products")
public class ProductController {

    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByCode(@PathParam("code") String code) {
        Product product = ofy().load().type(Product.class).id(code).now();
        return Response.ok(product).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Product> products = ofy().load().type(Product.class).list();
        if (products == null || products.isEmpty()) {
            Response.status(203).build();
        }
        GenericEntity<List<Product>> entity =
                new GenericEntity<List<Product>>(Lists.newArrayList(products)) {};
        return Response.ok(entity).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(String code) {
        Float price = new Random(5).nextFloat();
        Integer quantity = new Random(5).nextInt();
        Set<String> categories = new HashSet<>();
        Product newProduct =  new Product(code, "product " + code, price, quantity, categories, "brand", "description",
                "https://www.elegantthemes.com/blog/tips-tricks/how-to-create-custom-trackable-short-urls-for-all-your-wordpress-posts-and-pages");
        ofy().save().entity(newProduct).now();
        return Response.ok(newProduct).build();
    }
}
