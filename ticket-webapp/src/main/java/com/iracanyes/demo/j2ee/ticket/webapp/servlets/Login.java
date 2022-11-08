package com.iracanyes.demo.j2ee.ticket.webapp.servlets;



import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.iracanyes.demo.j2ee.ticket.business.forms.LoginForm;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    this.getServletContext()
        .getRequestDispatcher("/WEB-INF/jsp/page/login.jsp")
        .forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    LoginForm form = new LoginForm();
    form.checkLoginAndPassword(request);

    // Attach the Bean object that handle the submitted form to the request attributes object
    request.setAttribute("form", form);

    // Create cookie on the user client
    Cookie cookie = new Cookie("login", request.getParameter("login"));
    // Set max age in seconds.
    // Default duration : session (If browser is closed, the cookie is deleted).
    // Here, we set a max age of 30days.
    cookie.setMaxAge(60 * 60 * 24 * 30);
    response.addCookie(cookie);

    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/login.jsp")
            .forward(request, response);
  }

}