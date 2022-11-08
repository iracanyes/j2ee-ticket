package com.iracanyes.demo.j2ee.ticket.model.bean.ticket;

/**
 * Bean représentant le Niveau d'un Bug.
 *
 * @author  iracanyes
 */
public class BugLevel {
  /* ==================== Attributs ==================== */
  private Integer id;
  private Integer order;
  private String label;

  /* ==================== Constructeurs ==================== */
  // Default Constructor
  public BugLevel(){}

  /**
   * @param pId -
   */
  public BugLevel(Integer pId){
    id = pId;
  }


  /* ==================== Getters/Setters ==================== */

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  /* ==================== Méthodes ==================== */
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";
    vStB.append(" {")
            .append("id=").append(id)
            .append(vSEP).append("order=").append(order)
            .append(vSEP).append("label=\"").append(label).append('"')
            .append("}");
    return vStB.toString();
  }
}
