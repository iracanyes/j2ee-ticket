package com.iracanyes.demo.j2ee.ticket.model.bean.users;

public class User {
  private Integer id;
  private String firstname;
  private String lastname;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public String toString() {
    final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());

    final String  vSEP = ", ";

    vStB.append(" {")
            .append("id=\"").append(id).append('"')
            .append(vSEP).append("firstname=\"").append(firstname).append('"')
            .append(vSEP).append("lastname=\"").append(lastname).append("}");

    return vStB.toString();
  }
}
