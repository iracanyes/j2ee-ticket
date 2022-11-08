package com.iracanyes.demo.j2ee.ticket.model.search.ticket;

/**
 * Search Criteria class for Ticket
 */
public class SearchTicket {
  /* ==================== Attributs ==================== */
  private Integer projectId;
  private Integer authorId;

  /* ==================== Constructeurs ==================== */
  // Default constructor
  public SearchTicket(){}

  /* ==================== Getters/Setters ==================== */

  public Integer getProjectId() {
    return projectId;
  }

  public SearchTicket setProjectId(Integer projectId) {
    this.projectId = projectId;
    return this;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public SearchTicket setAuthorId(Integer authorId) {
    this.authorId = authorId;
    return this;
  }
}
