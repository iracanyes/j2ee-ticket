package com.iracanyes.demo.j2ee.ticket.model.bean.project;

/**
 * Objet métier représentant une Version de Projet
 *
 * @author lgu
 */
public class Version {
  /* ==================== Attributs ==================== */
  private Project project;
  private String number;

  /*  ==================== Constructeurs ==================== */
  /* Default constructor */
  public Version(){}

  /* ==================== Getters/Setters ==================== */

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  /* ==================== Méthodes ==================== */
  @Override
  public String toString(){
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";

    vStB.append(" {")
        .append(vSEP).append("number=\"").append(number).append('"')
        .append(vSEP).append("project=").append(project).append("}");

    return vStB.toString();
  }
}
