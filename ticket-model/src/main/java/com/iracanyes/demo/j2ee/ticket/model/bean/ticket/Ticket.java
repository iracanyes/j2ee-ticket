package com.iracanyes.demo.j2ee.ticket.model.bean.ticket;

import java.util.Date;
import com.iracanyes.demo.j2ee.ticket.model.bean.project.Project;
public class Ticket {
  /* ==================== Properties ==================== */
  private Long number;
  private String title;
  private Date date;
  private String description;
  private Project project;
  private TicketStatus status;

  /* ==================== Constructors ==================== */
  // Default constructor
  public Ticket(){}

  public Ticket(Long pNumber){
    number = pNumber;
  }

  /* ==================== Getters/Setters ==================== */

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  /* ==================== Methods ==================== */
  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
    final String vSEP = ", ";
    vStB.append(" {")
            .append("number=").append(number)
            .append(vSEP).append("title=\"").append(title).append('"')
            .append(vSEP).append("date=").append(date)
            .append(vSEP).append("description=\"").append(description).append('"')
            .append(vSEP).append("project=").append(project)
            .append(vSEP).append("status=").append(status)
            .append("}");
    return vStB.toString();
  }
}
