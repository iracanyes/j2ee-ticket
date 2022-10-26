package com.iracanyes.demo.j2ee.ticket.webapp.servlets;


import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Forms", value = "/Forms")
public class Forms extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    this.getServletContext()
        .getRequestDispatcher("/WEB-INF/jsp/page/forms.jsp")
        .forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    /*
    * Recall : It's not recommended to handle the form request in the servlet.
    * As a good pratice , create a Bean object in the Business logic to handle
    * Connection to other services.
    * */

    /* you can access form input inside the request parameter object */
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    /* Multi choice input */
    String[] selecteds = request.getParameterValues("selected");


    // Attach data to request
    request.setAttribute("firstname", firstname);
    request.setAttribute("lastname", lastname);
    request.setAttribute("activities", selecteds);

    // Dispatch to JSP page
    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/forms.jsp")
            .forward(request, response);

  }

}