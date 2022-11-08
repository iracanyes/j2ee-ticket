package com.iracanyes.demo.j2ee.ticket.model.bean.project;

import java.util.Date;
import com.iracanyes.demo.j2ee.ticket.model.bean.users.User;

/**
 * Objet métier représentant un Projet
 *
 * @author iracanyes
 */
public class Project {
  private Integer id;
  private String name;
  private Date dateCreation;
  private Boolean closed;
  private User manager;

  /*
  * Default constructor
  * */
  public Project(){

  }

  /**
   * @param pId -
   */
  public Project(Integer pId){
    id = pId;
  }

  /* ==================== Getters/Setters ==================== */

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  public Boolean getClosed() {
    return closed;
  }

  public void setClosed(Boolean closed) {
    this.closed = closed;
  }

  public User getManager() {
    return manager;
  }

  public void setManager(User manager) {
    this.manager = manager;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());

    final String  vSEP = ", ";

    vStB.append(" {")
        .append(vSEP).append("name=\"").append(name).append('"')
        .append(vSEP).append("dateCreation=\"").append(dateCreation).append('"')
        .append(vSEP).append("closed=\"").append(closed).append("}");

    return vStB.toString();
  }
}
