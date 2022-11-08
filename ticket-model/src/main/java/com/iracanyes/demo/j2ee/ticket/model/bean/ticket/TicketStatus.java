package com.iracanyes.demo.j2ee.ticket.model.bean.ticket;

/**
 * Bean représentant le Statut d'un Ticket.
 *
 * @author lgu
 */
public class TicketStatus {
  /* ==================== Properties ==================== */
  private Integer id;
  private String label;
  /* ==================== Constructors ==================== */
  // Default constructor
  public TicketStatus(){}

  public TicketStatus(Integer pId ){
    id = pId;
  }
  /* ==================== Getters/Setters ==================== */

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  /* ==================== Methods ==================== */
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";
    vStB.append(" {")
            .append("id=").append(id)
            .append(vSEP).append("label=\"").append(label).append('"')
            .append("}");
    return vStB.toString();
  }
}
