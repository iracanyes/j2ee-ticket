/*
* Description :
* This Bean object handle login connection to the app.
* It simulates the result returned from
* Database request for existing user.
*
* */
package com.iracanyes.demo.j2ee.ticket.business.forms;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginForm {

  private String result;

  /*
  * Simulate a call to a service consumer (database) looking for an existing user.
  * Here we check that the password is equal to (login value +"123")
  * */
  public void checkLoginAndPassword(HttpServletRequest request){
    String login = request.getParameter("login");
    String password = request.getParameter("password");



    // Here we check that the password is equal to login value followed 123
    // and store a result in the property result of the object
    if(password.equals(login + "123")){
      result = "You're connected";

      // Here, we create a session which save some user data on the server
      // for the entire visit on the website.
      // request.getSession retrieve an active session or create a new session
      // The method can be used to access session in other part of the application
      HttpSession session = request.getSession();
      // Set login of the user in the session
      session.setAttribute("login", login);

      // Here is a sample of cookies manipulation:
      // we access data stored in the cookies attached to the request
      Cookie[] cookies = request.getCookies();
      if(cookies != null){
        for(Cookie cookie: cookies){
          if(cookie.getName().equals("login")){
            request.setAttribute("username", login);
          }
        }
      }
    }else{
      result = "Login or password is incorrect.";
    }


  }

  public void disconnect(HttpServletRequest request){
    // Delete current session
    HttpSession session = request.getSession();
    session.invalidate();
  }

  /* Getter and Setter */
  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }


}
