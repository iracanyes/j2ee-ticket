package com.iracanyes.demo.j2ee.ticket.webapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
//import java.io.PrintWriter;
//import java.io.IOException;

@WebServlet(name="About", value="/About")
public class About extends HttpServlet{
  public About(){
    super();
    // TODO
  }

  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException{
    // TODO
    // HttpServletRequest

    /* HttpServletResponse */
    // Définir le type de contenu retourné
    response.setContentType("text/html");
    // UTF-8 gére les accents
    response.setCharacterEncoding("UTF-8");

    // Dispatch to JSP file "about.jsp" which will handle the vue's creatiib to return
    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/about.jsp")
            .forward(request, response);
  }

  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException{
    // TODO
  }

}