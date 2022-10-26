package com.iracanyes.demo.j2ee.ticket.resources;


import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;

@Path("/hello")
public class HelloResource {

  @GET
  public String sayHello(){
    return "Hello world";
  }
}
