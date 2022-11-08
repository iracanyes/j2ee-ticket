package com.iracanyes.demo.j2ee.ticket.webapp.servlets;



import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.iracanyes.demo.j2ee.ticket.model.bean.intro.Author;

@WebServlet(name = "Jstl", value = "/Jstl")
public class Jstl extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");

    String[] titles = {"Maven", "Jakarta", "Java", "JSTL"};

    request.setAttribute("titles", titles);

    Author author = new Author("Albator", "Gaia", "https://iracanyes.com/books/author/gaia/albator/films?title=corsaire_de_l_espace", false);

    request.setAttribute("author", author);

    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/java_standard_tag_library.jsp")
            .forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // TODO: create form

  }

}