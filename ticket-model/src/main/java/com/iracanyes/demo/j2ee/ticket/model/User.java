package com.iracanyes.demo.j2ee.ticket.model;

public class User {
  private String email;
  private String password;

  public User(String email, String password){
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
