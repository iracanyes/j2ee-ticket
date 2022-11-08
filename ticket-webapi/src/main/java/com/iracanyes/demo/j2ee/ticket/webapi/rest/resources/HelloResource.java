package com.iracanyes.demo.j2ee.ticket.webapi.rest.resources;


import com.iracanyes.demo.j2ee.ticket.model.bean.intro.FullName;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/hello")
public class HelloResource {

  public HelloResource(){}

  /**
   * You can test the end-point with the following command:
   *  curl http://localhost:8077/ticket_webapi/api/hello -H "Accept: text/plain"
   * @return String -
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello(){
    return "Hello world";
  }

  /**
   * You can test the end-point with the following command:
   *  curl http://localhost:8077/ticket_webapi/api/hello -H "Accept: application/xml"
   * @return String -
   */
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public String sayHelloXml(){
    return "Hello world";
  }

  /**
   * You can test the end-point with the following command:
   *  curl http://localhost:8077/ticket_webapi/api/hello -H "Accept: text/html"
   * @return String -
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHelloHTML(){
    return "<html><head><title>API - Hello World</title><head><body><h1>Hello world</h1></body></html>";
  }

  /**
   * You can test the end-point with the following command:
   *  curl http://localhost:8077/ticket_webapi/api/hello -H "Accept: application/json"
   * @return FullName -
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public FullName sayMyName(){
    return new FullName("John", "Doe");
  }
}
