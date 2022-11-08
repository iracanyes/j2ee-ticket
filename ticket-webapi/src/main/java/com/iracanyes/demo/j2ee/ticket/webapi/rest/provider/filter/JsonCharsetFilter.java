package com.iracanyes.demo.j2ee.ticket.webapi.rest.provider.filter;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonCharsetFilter implements ContainerResponseFilter {
  @Override
  public void filter(
          ContainerRequestContext pRequestContext,
          ContainerResponseContext pResponseContext
  ) throws IOException{
    pResponseContext.getHeaders().putSingle(
            "Content-type",
            MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=utf-8"
    );
  }
}
