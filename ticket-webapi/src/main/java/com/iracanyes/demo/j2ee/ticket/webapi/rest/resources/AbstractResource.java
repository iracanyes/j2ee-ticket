package com.iracanyes.demo.j2ee.ticket.webapi.rest.resources;

import com.iracanyes.demo.j2ee.ticket.business.ManagerFactory;

public abstract class AbstractResource {
  private static ManagerFactory managerFactory;

  /*
  * ManagerFactory is accessible only from child resources
  * */
  protected static ManagerFactory getManagerFactory() {
    return managerFactory;
  }

  /*
  * ManagerFactory of a resource can be set from other part of the application.
  * Allow to change the ManagerFoctory of a resource
  * */
  public static void setManagerFactory(ManagerFactory managerFactory) {
    AbstractResource.managerFactory = managerFactory;
  }
}
