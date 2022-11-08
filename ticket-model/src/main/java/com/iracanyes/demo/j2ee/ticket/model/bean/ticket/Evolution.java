package com.iracanyes.demo.j2ee.ticket.model.bean.ticket;

/**
 * Bean représentant un Ticket de type "Évolution".
 *
 * @author iracanyes
 */
public class Evolution extends Ticket{
  /* ==================== Attributs ==================== */
  private Integer priority;

  /* ==================== Constructors ==================== */
  // Default constructor
  public Evolution(){}

  /**
   * @param pNumber -
   */
  public Evolution(long pNumber){
    super(pNumber);
  }

  /* ==================== Getters/Setters ==================== */

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  /* ==================== Methods ==================== */
  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";
    vStB.append(" {")

            .append('(')
            .append(Ticket.class.getSimpleName())
            .append(") ")
            .append(super.toString())
            .append(" + {")

            .append("priority=").append(priority)
            .append("}");
    return vStB.toString();
  }
}
