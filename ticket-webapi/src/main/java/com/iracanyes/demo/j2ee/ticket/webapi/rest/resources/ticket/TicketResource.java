package com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.ticket;

import com.iracanyes.demo.j2ee.ticket.business.ManagerFactory;
import com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.AbstractResource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import com.iracanyes.demo.j2ee.ticket.business.manager.TicketManager;
import com.iracanyes.demo.j2ee.ticket.model.bean.ticket.Ticket;
import com.iracanyes.demo.j2ee.ticket.model.exception.NotFoundException;
import com.iracanyes.demo.j2ee.ticket.model.search.ticket.SearchTicket;


import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource extends AbstractResource {

  /**
   *
   */
  @GET
  @Path("{number}")
  public Ticket get(@PathParam("number") Long pNumber) throws NotFoundException{
    TicketManager vTicketManager = getManagerFactory().getTicketManager();
    Ticket vTicket = vTicketManager.getTicket(pNumber);
    return vTicket;
  }

  @GET
  public List<Ticket> search(@QueryParam("projectId") Integer vProjectId){
    TicketManager vTicketManager = getManagerFactory().getTicketManager();
    List<Ticket> vListTicket = vTicketManager.getListTicket(new SearchTicket());
    return vListTicket;
  }
}
