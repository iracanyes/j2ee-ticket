package com.iracanyes.demo.j2ee.ticket.model.bean.intro;

import com.iracanyes.demo.j2ee.ticket.model.exception.ModelException;

/**
 * FullName class
 *
 */
public class FullName
{
  private long id;
  private String firstname;
  private String lastname;

  public FullName(){

  }

  public FullName(String firstname, String lastname){
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) throws ModelException {
    if(firstname.length() > 45){
      throw new ModelException("Firstname length is too long. (Maximum 45 characters)");
    }
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
    return "FullName{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
