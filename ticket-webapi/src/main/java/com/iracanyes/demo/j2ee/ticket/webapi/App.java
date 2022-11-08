package com.iracanyes.demo.j2ee.ticket.webapi;

import com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.HelloResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 */
@ApplicationPath("api")
public class App extends ResourceConfig
{
  public App(){
    registerClasses(HelloResource.class);
    register("com.iracanyes.demo.j2ee.ticket.webapi.rest");
  }
}
