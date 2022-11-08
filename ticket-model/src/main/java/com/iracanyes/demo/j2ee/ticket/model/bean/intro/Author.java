package com.iracanyes.demo.j2ee.ticket.model.bean.intro;

/**
 * Author class
 *
 */
public class Author
{
  private long id;
  private String firstname;
  private String lastname;
  private String website;
  private boolean active;

  public Author(){}

  public Author(String firstname, String lastname){
    this.firstname = firstname;
    this.lastname = lastname;
    this.website = "https://iracanyes.com";
    this.active = true;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Author(String firstname, String lastname, String website, boolean active){
    this.firstname = firstname;
    this.lastname = lastname;
    this.website = website;
    this.active = active;
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

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Author{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", website='" + website + '\'' +
            ", isActive=" + active +
            '}';
  }
}
