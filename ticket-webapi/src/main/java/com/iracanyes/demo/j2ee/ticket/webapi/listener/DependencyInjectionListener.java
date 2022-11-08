/*
* Description: Here we define a dependency injection listener which implements ServletContextListener
* It will be responsible of creating the ManagerFactory and all resource managers.
* And inject the ManagerFactory instance in the AbstractResource class so that all resource can access managers.
* */
package com.iracanyes.demo.j2ee.ticket.webapi.listener;

import com.iracanyes.demo.j2ee.ticket.business.ManagerFactory;
import com.iracanyes.demo.j2ee.ticket.business.manager.ProjectManager;
import com.iracanyes.demo.j2ee.ticket.business.manager.TicketManager;
import com.iracanyes.demo.j2ee.ticket.webapi.rest.resources.AbstractResource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DependencyInjectionListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce){
    // Create an instance of ManagerFactory
    ManagerFactory mf = new ManagerFactory();

    // We instantiate all managers of the ManagerFactory
    mf.setProjectManager(new ProjectManager());
    mf.setTicketManager(new TicketManager());

    // Injection of the ManagerFactory instance in the AbstractResource
    AbstractResource.setManagerFactory(mf);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce){
    // TODO
  }
}
