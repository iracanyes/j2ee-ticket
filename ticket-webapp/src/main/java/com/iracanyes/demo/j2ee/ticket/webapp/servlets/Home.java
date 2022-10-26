package com.iracanyes.demo.j2ee.ticket.webapp.servlets;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Home", value = "/Home")
public class Home extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    // Pass to home.jsp
    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/home.jsp")
            .forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO: 1. Create form

  }

}