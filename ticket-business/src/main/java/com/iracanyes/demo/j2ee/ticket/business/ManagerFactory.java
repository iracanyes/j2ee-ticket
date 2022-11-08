/**
 * Description: Use Factory Pattern to show Dependencies inversion
 * Business Layer defines the methods that call the consumers
* */
package com.iracanyes.demo.j2ee.ticket.business;

import com.iracanyes.demo.j2ee.ticket.business.manager.ProjectManager;
import com.iracanyes.demo.j2ee.ticket.business.manager.TicketManager;

/**
 *
 */
public class ManagerFactory {
  private ProjectManager projectManager;
  private TicketManager ticketManager;

  public ProjectManager getProjectManager(){
    return projectManager;
  }

  public void setProjectManager(ProjectManager projectManager) {
    this.projectManager = projectManager;
  }

  public void setTicketManager(TicketManager vTicketManager) {
    this.ticketManager = vTicketManager;
  }

  public TicketManager getTicketManager(){
    return ticketManager;
  }


}
