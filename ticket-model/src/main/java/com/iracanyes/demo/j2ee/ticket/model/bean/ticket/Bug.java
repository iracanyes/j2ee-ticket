package com.iracanyes.demo.j2ee.ticket.model.bean.ticket;

import com.iracanyes.demo.j2ee.ticket.model.bean.project.Version;

import java.util.ArrayList;
import java.util.List;

public class Bug extends Ticket{
  /* ==================== Attributs ==================== */
  private BugLevel bugLevel;
  private final List<Version> listVersionAffected = new ArrayList();

  /* ==================== Constructors ==================== */
  // Default constructor
  public Bug(){

  }

  public Bug(Long pNumber){
    super(pNumber);
  }

  /* ==================== Getters/Setters ==================== */

  public BugLevel getBugLevel() {
    return bugLevel;
  }

  public void setBugLevel(BugLevel bugLevel) {
    this.bugLevel = bugLevel;
  }

  public List<Version> getListVersionAffected() {
    return listVersionAffected;
  }

  /* ==================== Méthodes ==================== */

  @Override
  public String toString(){
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";

    vStB.append("{")
        .append('(').append(Ticket.class.getSimpleName()).append(')')
        .append(super.toString())
        .append(" {").append("bugLevel=").append(bugLevel).append("}");

    return vStB.toString();
  }
}
