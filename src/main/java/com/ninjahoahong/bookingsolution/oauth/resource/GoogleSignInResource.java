package com.ninjahoahong.bookingsolution.oauth.resource;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.ninjahoahong.bookingsolution.entities.User;
import com.ninjahoahong.bookingsolution.entities.UserRole;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Path("google")
public class GoogleSignInResource {

    private static final String CLIENT_ID = "YOUR_ID.apps.googleusercontent.com";
    private static final JacksonFactory jacksonFactory = new JacksonFactory();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserWithGoogleToken(OauthSignInRequestBody oauthSignInRequestBody)
            throws GeneralSecurityException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                UrlFetchTransport.getDefaultInstance(), jacksonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(oauthSignInRequestBody.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            User user = ofy().load().type(User.class).id(payload.getEmail()).now();

            if (user == null) {
                user = ofy().transact(() -> {
                    System.out.println("new user user");
                    User newUser = new User(payload.getEmail(), oauthSignInRequestBody.getFullName(), UserRole.CUSTOMER);
                    ofy().save().entity(newUser).now();
                    return newUser;
                });
            }

            return Response.status(200).entity(user).build();
        } else {
            return Response.status(401).build();
        }
    }
}
