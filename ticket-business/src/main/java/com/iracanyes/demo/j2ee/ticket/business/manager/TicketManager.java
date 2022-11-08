package com.iracanyes.demo.j2ee.ticket.business.manager;

import com.iracanyes.demo.j2ee.ticket.model.bean.project.Project;
import com.iracanyes.demo.j2ee.ticket.model.bean.ticket.Bug;
import com.iracanyes.demo.j2ee.ticket.model.bean.ticket.Evolution;
import com.iracanyes.demo.j2ee.ticket.model.bean.ticket.Ticket;
import com.iracanyes.demo.j2ee.ticket.model.exception.NotFoundException;
import com.iracanyes.demo.j2ee.ticket.model.search.ticket.SearchTicket;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {
  /**
   * Search and return the {@link Ticket} number {@code pNumber}
   *
   * @param pNumber Number of the ticket
   * @return The {@link Ticket}
   * @throws NotFoundException
   */
  public Ticket getTicket(Long pNumber) throws NotFoundException{
    // TODO: Implement the DAO
    if(pNumber < 1){
      throw new NotFoundException("Ticket not found: number=" + pNumber);
    }

    Evolution vEvolution = new Evolution(pNumber);
    vEvolution.setPriority(10);

    return vEvolution;
  }

  /**
   * Retrieve the list of {@link Ticket} corresponding to criteria
   *
   * @param pSearchTicket -
   * @return List
   */
  public List<Ticket> getListTicket(SearchTicket pSearchTicket){
    // TODO: Implement the DAO
    List<Ticket> vList = new ArrayList<>();

    if(pSearchTicket.getProjectId() != null){
      Project vProject = new Project(pSearchTicket.getProjectId());

      for(int vI = 0; vI < 4; vI++){
        Ticket vTicket = new Bug((long) pSearchTicket.getProjectId() * 10 + vI);
        vTicket.setProject(vProject);
        vList.add(vTicket);
      }
    }else {
      for(int vI= 0; vI < 9; vI++){
        Ticket vTicket = new Evolution((long) vI);
        vList.add(vTicket);
      }
    }

    return vList;
  }
}
