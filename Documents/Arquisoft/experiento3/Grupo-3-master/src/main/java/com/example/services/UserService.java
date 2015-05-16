/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import co.edu.uniandes.csw.miso4204.security.jwt.api.JsonWebToken;
import co.edu.uniandes.csw.miso4204.security.jwt.api.JwtHashAlgorithm;
import co.edu.uniandes.csw.miso4204.security.logic.dto.UserDTO;
import com.google.gson.Gson;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.tenant.Tenant;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.stormpath.sdk.application.Application;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jhonatan
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @Path("/login")
    @POST
    public Response login(UserDTO user) {
        int status = 500; //Codigo de error en el servidor
        String token = "User and/or password wrong";
        UserDTO userStorm = new UserDTO();
        String path = ".\\apiKey-8HBI62GZVOQU1NY9P44Y7JXSX.properties";
        ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
        Client client = Clients.builder().setApiKey(apiKey).build();

        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("Clinica")));
            Application application = applications.iterator().next();

            AuthenticationResult result = application.authenticateAccount(request);
            Account account = result.getAccount();
            userStorm.setEmail(account.getEmail());
            userStorm.setName(account.getFullName());
            userStorm.setUsername(account.getUsername());
            userStorm.setPassword(user.getPassword());
            userStorm.setLevelAccess("User");
            token = new Gson().toJson(JsonWebToken.encode(userStorm, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
            status = 200;

        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }

        return Response.status(status).entity(token).build();
    }

    @Path("/login")
    @GET
    public Response login() {
        int status = 500; //Codigo de error en el servidor
        String token = "User and/or password wrong";
        

        return Response.status(status).entity(token).build();
    }
}
